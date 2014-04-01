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

import org.foxbpm.engine.impl.identity.Authentication;
import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.task.CommentImpl;
import org.foxbpm.engine.impl.util.ClockUtil;
import org.foxbpm.engine.impl.util.GuidUtil;

/**
 * @author kenshin
 */
public class AddCommentCmd implements Command<Object> {

	protected String taskId;
	protected String processInstanceId;
	protected String message;
	protected String fullMessage;

	public AddCommentCmd(String taskId, String processInstanceId, String message, String fullMessage) {
		this.taskId = taskId;
		this.processInstanceId = processInstanceId;
		this.message = message;
		this.fullMessage = fullMessage;

	}

	public Object execute(CommandContext commandContext) {
		String userId = Authentication.getAuthenticatedUserId();
		CommentImpl comment = new CommentImpl();
		comment.setId(GuidUtil.CreateGuid());
		comment.setUserId(userId);
		comment.setType(CommentImpl.TYPE_COMMENT);
		comment.setTime(ClockUtil.getCurrentTime());
		comment.setTaskId(taskId);
		comment.setProcessInstanceId(processInstanceId);
		// comment.setAction(Event.ACTION_ADD_COMMENT);

		comment.setMessage(message);

		comment.setFullMessage(fullMessage);

		commandContext.getCommentManager().insert(comment);

		return null;
	}
}
