package com.parqueape.infrastructure;

import org.json.JSONObject;

public class PresentationUtil {
	public static String response(String message, JSONObject data) {
		JSONObject obj = new JSONObject();
    	
        obj.put("data", data);
        obj.put("message", message);
        
        return obj.toString();
	}
	
	public static String error(String message) {
		JSONObject obj = new JSONObject();
    	
        obj.put("message", message);
        
        return obj.toString();
	}
}
