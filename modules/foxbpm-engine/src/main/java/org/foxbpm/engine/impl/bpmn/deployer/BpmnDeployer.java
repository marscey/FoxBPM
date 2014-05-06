/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 * @author ych
 */
package org.foxbpm.engine.impl.bpmn.deployer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.foxbpm.engine.exception.FoxBPMBizException;
import org.foxbpm.engine.impl.Context;
import org.foxbpm.engine.impl.entity.DeploymentEntity;
import org.foxbpm.engine.impl.entity.ProcessDefinitionEntity;
import org.foxbpm.engine.impl.entity.ResourceEntity;
import org.foxbpm.engine.impl.persistence.ProcessDefinitionManager;
import org.foxbpm.engine.impl.persistence.deploy.Deployer;
import org.foxbpm.engine.impl.util.GuidUtil;
import org.foxbpm.engine.modelparse.ProcessModelParseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BpmnDeployer implements Deployer {
	
	
	Logger log = LoggerFactory.getLogger(BpmnDeployer.class);
	public static final String BPMN_RESOURCE_SUFFIX = "bpmn";

	public static final String DIAGRAM_SUFFIXES = "png";
	protected ProcessModelParseHandler processModelParseHandler;

	public ProcessModelParseHandler getProcessModelParseHandler() {
		return processModelParseHandler;
	}

	public void deploy(DeploymentEntity deployment) {
		ResourceEntity resourceBpmn = null;
		Map<String, ResourceEntity> resources = deployment.getResources();
		for (String resourceName : resources.keySet()) {
			if (resourceName.toLowerCase().endsWith(BPMN_RESOURCE_SUFFIX)) {
				resourceBpmn = resources.get(resourceName);
				log.info("资源" + resourceBpmn.getName() + "发布");
			} 
		}
		if(resourceBpmn == null){
			throw new FoxBPMBizException("发布文件中不存在.bpmn文件");
		}
		InputStream input = new ByteArrayInputStream(resourceBpmn.getBytes());
		ProcessDefinitionManager processDefinitionManager = Context.getCommandContext().getProcessDefinitionManager();
		ProcessDefinitionEntity processEntity = (ProcessDefinitionEntity)processModelParseHandler.createProcessDefinition("dddd", input);
		ProcessDefinitionEntity latestProcessDefinition = processDefinitionManager.findLatestProcessDefinitionByKey(processEntity.getKey());
		if(deployment.getUpdateDeploymentId() == null || deployment.getUpdateDeploymentId().equals("")){
			//发布
			int processDefinitionVersion = 1;
			if (latestProcessDefinition != null) {
				processDefinitionVersion = latestProcessDefinition.getVersion() + 1;
			}
			//新的发布号
			processEntity.setDeploymentId(deployment.getId());
			//新的版本号
			processEntity.setVersion(processDefinitionVersion);
			String processDefinitionId = processEntity.getKey() + ":" + processEntity.getVersion() + ":" + GuidUtil.CreateGuid(); // GUID
			//新的定义ID
			processEntity.setProcessDefinitionId(processDefinitionId);
			processDefinitionManager.insert(processEntity);
		}else{
			//更新
			
			
		}
		
	}
	
	 public void setProcessModelParseHandler(ProcessModelParseHandler processModelParseHandler) {
		    this.processModelParseHandler = processModelParseHandler;
	}

}