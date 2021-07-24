package com.example.clientLambdaExample.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	
	public static String converObjectToJson(Object t) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

}
