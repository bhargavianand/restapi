package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author hp
 * 
 * This utility is to parse the JSON for response assertions.
 *
 */
public class TestUtil {
	
	
	public JSONObject responseJson;
	public String getValueByJPath(String jpath)
	{
		Object obj = responseJson;   //is this upcasting?
		for(String s : jpath.split("/")) {
			if(!s.isEmpty()) {
					if(!(s.contains("[")|| s.contains("]"))) {
						obj = ((JSONObject)obj).get(s);                              //casting obj to JSONObject
								
					}
					else if(s.contains("[")|| s.contains("]")) {
						obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]","")));
					}
				
			}
			
		}
		return obj.toString();
	}
		
	
}
