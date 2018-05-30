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
}
