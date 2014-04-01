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

import java.util.ArrayList;
import java.util.List;


import org.foxbpm.engine.impl.bpmn.behavior.TaskCommandInst;
import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.task.TaskInstanceEntity;
import org.foxbpm.engine.impl.util.CoreUtil;
import org.foxbpm.engine.task.TaskInstanceType;

public class GetTaskCommandByTaskIdCmd implements Command<List<TaskCommandInst>>{

	protected String taskId;
	protected boolean isProcessTracking;
	public GetTaskCommandByTaskIdCmd(String taskId,boolean isProcessTracking){
		this.taskId=taskId;
		this.isProcessTracking=isProcessTracking;
	}
	
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		List<TaskCommandInst> taskCommandInsts=new ArrayList<TaskCommandInst>();
		
		TaskInstanceEntity taskInstance=commandContext.getTaskManager().findTaskById(taskId);
		
		if(isProcessTracking){
			//流程追踪查询
			if(taskInstance!=null){
				if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK){
					taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance,this.isProcessTracking);
				}
				
			}
		}
		else{

			//非流程追踪查询
			if(taskInstance!=null){
				if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK||taskInstance.getTaskInstanceType()==TaskInstanceType.FIXNOTICETASK){
					taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance,this.isProcessTracking);
				}
				
			}
		}
		
		
		return taskCommandInsts;
	}

}
