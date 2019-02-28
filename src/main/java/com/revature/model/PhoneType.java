package com.revature.model;

public enum PhoneType {
	
	CELL("Cell"), HOME("Home"), WORK("Work");
	
	private String type;
	
	PhoneType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
}
