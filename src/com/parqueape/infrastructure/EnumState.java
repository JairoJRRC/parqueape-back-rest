package com.parqueape.infrastructure;

public enum EnumState {
	ACTIVE, INACTIVE;

	public String toString() {
		switch (this) {
		case ACTIVE:
			return "activo";
		case INACTIVE:
			return "inactivo";
		default:
			return "";
		}
	}
	
	public static EnumState getRole(String role) {
		EnumState response = null;

		switch (role) {
		case "ACTIVE":
			response = EnumState.ACTIVE;
			break;
		case "INACTIVE":
			response = EnumState.INACTIVE;
			break;
		}

		return response;
	}

	public static String getValue(EnumState role) {
		String response = null;

		switch (role) {
		case ACTIVE:
			response = "ACTIVE";
			break;
		case INACTIVE:
			response = "INACTIVE";
			break;
		}

		return response;
	}
}
