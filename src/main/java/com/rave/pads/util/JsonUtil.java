package com.rave.pads.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
    
    public static String toJson(Object obj){
    	String jsonString = null;
        try {
			ObjectMapper mapper = new ObjectMapper();
			//Converting the Object to JSONString
			jsonString = mapper.writeValueAsString(obj);		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonString;
    }    
}
