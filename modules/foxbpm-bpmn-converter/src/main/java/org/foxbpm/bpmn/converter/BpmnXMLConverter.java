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
 * @author yangguangftlp
 */
package org.foxbpm.bpmn.converter;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.foxbpm.bpmn.constants.BpmnXMLConstants;
import org.foxbpm.model.BpmnModel;

/**
 * BpmnXML转换类
 * 
 * @author yangguangftlp
 * @date 2014年10月15日
 */
public class BpmnXMLConverter implements BpmnXMLConstants {
	
	// protected static final Logger LOGGER =
	// LoggerFactory.getLogger(BpmnXMLConverter.class);
	
	protected static Map<String, BaseElementXMLConverter> convertersToBpmnMap = new HashMap<String, BaseElementXMLConverter>();
	static {
		// events
		addConverter(new EndEventXMLConverter());
		addConverter(new StartEventXMLConverter());
		
		// tasks
		addConverter(new UserTaskXMLConverter());
		addConverter(new CallActivityXMLConverter());
	}
	
	public static void addConverter(BaseElementXMLConverter converter) {
		convertersToBpmnMap.put(converter.getXMLElementName(), converter);
	}
	
	public BpmnModel convertToBpmnModel(Document doc) {
		BpmnModel model = new BpmnModel();
		Element definitions = doc.getRootElement();
		String nodeName = definitions.getName();
		// definitions
		if (ELEMENT_DEFINITIONS.equals(nodeName)) {
			// ...
			/*List nodeList = definitions.elements();
			int length = nodeList.getLength();
			for (int i = 0; i < length; i++) {
				node = nodeList.item(i);
				nodeName = BpmnXMLUtil.getEleLoclaName(node.getNodeName());
				// process
				if (ELEMENT_PROCESS.equals(nodeName)) {
					
					// BPMNDiagram
				} else if (ELEMENT_DI_DIAGRAM.equals(nodeName)) {
					
				}
			}*/
		}
		return model;
	}
}