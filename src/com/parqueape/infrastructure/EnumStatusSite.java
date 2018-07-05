package com.parqueape.infrastructure;

public enum EnumStatusSite {
	AVAILABLE, BUSY, RESERVED;

	public String toString() {
		switch (this) {
		case AVAILABLE:
			return "DISPONIBLE";
		case BUSY:
			return "OCUPADO";
		case RESERVED:
			return "RESERVADO";
		default:
			return "";
		}
	}
	
	public static EnumStatusSite get(String enums) {
		EnumStatusSite response = null;

		switch (enums) {
		case "DISPONIBLE":
			response = EnumStatusSite.AVAILABLE;
			break;
		case "BUSY":
			response = EnumStatusSite.BUSY;
			break;
		case "RESERVADO":
			response = EnumStatusSite.RESERVED;
			break;
		}

		return response;
	}
}

