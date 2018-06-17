package com.parqueape.infrastructure;

public enum EnumTurn {
	MORNING, AFTERNOON, EVENING;

	public static EnumTurn getRole(String role) {
		EnumTurn response = null;

		switch (role) {
		case "MORNING":
			response = EnumTurn.MORNING;
			break;
		case "AFTERNOON":
			response = EnumTurn.AFTERNOON;
			break;
		case "EVENING":
			response = EnumTurn.EVENING;
			break;
		}

		return response;
	}

	public static String getValue(EnumTurn role) {
		String response = null;

		switch (role) {
		case MORNING:
			response = "MORNING";
			break;
		case AFTERNOON:
			response = "AFTERNOON";
			break;
		case EVENING:
			response = "EVENING";
			break;
		}

		return response;
	}
}
