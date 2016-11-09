package com.user.reg.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JsonConverterUtil<O, J extends JsonUtil<O, J>> {
	
	public List<J> convertToJsons(List<O> objects, Class<? extends JsonUtil<O, J>> jsonClass) throws InstantiationException, 
		IllegalAccessException {
		
		List<J> jsons = new ArrayList<>();
		for(O o : objects) {
			jsons.add(convertToJson(o, jsonClass));
		}
		
 		return jsons;
	}
	
	public J convertToJson(O object, Class<? extends JsonUtil<O, J>> jsonClass) throws InstantiationException, 
		IllegalAccessException {
		
		J json = createJsonObject(jsonClass);
		
		return json.convertToJson(object);
	}
	
	@SuppressWarnings("unchecked")
	public J createJsonObject(Class<? extends JsonUtil<O, J>> jsonClass) throws InstantiationException, 
		IllegalAccessException {
	
		return (J) jsonClass.newInstance();
	}
		
}
