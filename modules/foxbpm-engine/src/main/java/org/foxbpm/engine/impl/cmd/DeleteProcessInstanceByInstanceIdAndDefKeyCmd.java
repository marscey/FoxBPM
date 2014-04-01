/**
 * Copyright 1996-2014 FoxBPM Co.,Ltd.
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
 */
package org.foxbpm.engine.impl.cmd;



import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.runtime.ProcessInstanceEntity;

public class DeleteProcessInstanceByInstanceIdAndDefKeyCmd implements Command<Boolean> {

	
	
	protected String processDefinitionKey;
	protected String businessKey;
	protected boolean cascade;
	
	
	
	
	public DeleteProcessInstanceByInstanceIdAndDefKeyCmd(String processDefinitionKey,String businessKey, boolean cascade){
		
		this.processDefinitionKey=processDefinitionKey;
		this.businessKey=businessKey;
		this.cascade=cascade;
		
	}
	
	
	public Boolean execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		ProcessInstanceEntity processInstanceQueryTo=commandContext.getProcessInstanceManager().findProcessInstanceByDefKeyAndBusinessKey(processDefinitionKey, businessKey);
		if(processInstanceQueryTo==null){
			return false;
		}
		String  processInstanceId=processInstanceQueryTo.getId();
		
		commandContext.getProcessInstanceManager().deleteProcessInstance(processInstanceId, cascade);
		
		return true;
	}
	
	
	

}
