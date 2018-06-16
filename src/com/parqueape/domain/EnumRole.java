package com.parqueape.domain;

public enum EnumRole {
    ADMIN, 
    COMPANY,
    EMPLOYEE;
	
	public static EnumRole getRole(String role) {
	    EnumRole response = null;
	    
	    switch (role) {
	        case "ADMIN":
	            response = EnumRole.ADMIN;
	            break;
	        case "COMPANY":
	        	response = EnumRole.COMPANY;
	            break;
	    }
	    
	    return response;
	}
	
	public static String getValue(EnumRole role) {
	    String response = null;
	    
	    switch (role) {
	        case ADMIN:
	            response = "ADMIN";
	            break;
	        case COMPANY:
	        	response = "COMPANY";
	            break;
	    }
	    
	    return response;
	}
}

