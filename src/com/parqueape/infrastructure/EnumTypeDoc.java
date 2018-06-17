package com.parqueape.infrastructure;

public enum EnumTypeDoc {
	DNI,
	CARD;
	
	public static EnumTypeDoc getRole(String role) {
		EnumTypeDoc response = null;

		switch (role) {
		case "DNI":
			response = DNI;
			break;
		case "CARD":
			response = CARD;
			break;
		}

		return response;
	}

	public static String getValue(EnumTypeDoc role) {
		String response = null;

		switch (role) {
		case DNI:
			response = "DNI";
			break;
		case CARD:
			response = "CARD";
			break;
		}

		return response;
	}
}
