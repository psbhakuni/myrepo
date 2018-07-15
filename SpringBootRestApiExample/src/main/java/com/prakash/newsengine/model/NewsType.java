package com.prakash.newsengine.model;

public enum NewsType {
	GENERAL(1, "General"),
	FINANCE(2, "Finance"),
	SPORTS(3, "Sports");
	
	int id;
	String type;
	
	private NewsType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

}
