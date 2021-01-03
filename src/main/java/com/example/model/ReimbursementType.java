package com.example.model;

public class ReimbursementType {
	private int typeID;
	private String type;
	
	public ReimbursementType() {
		// TODO Auto-generated constructor stub
	}

	public ReimbursementType(int typeID, String type) {
		super();
		this.typeID = typeID;
		this.type = type;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementType [typeID=" + typeID + ", type=" + type + "]";
	}
}
