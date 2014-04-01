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
package org.foxbpm.engine.impl.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.foxbpm.engine.impl.expression.ExpressionMgmt;
import org.foxbpm.engine.runtime.ExecutionContext;

public class AssigneeUtil {
	
	
	public static List<String> executionExpression(String expression, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();

		Object result = ExpressionMgmt.execute(expression, executionContext);
		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}
			if (result instanceof BigDecimal) {
				resultList.add(result.toString());
			}
	

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}
	
	
	public static List<String> executionExpressionObj(Object result, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();


		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}
			if (result instanceof BigDecimal) {
				resultList.add(result.toString());
			}
	

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}


}
