package com.prakash.newsengine.model;

public enum NewsPriority {
	NORMAL(1, "Normal"),
	BREAKING(2, "Breaking"),
	ALL(1, "All");
	
	private int id;
	private String newsType;
	
	private NewsPriority(int id, String newsType) {
		this.id = id;
		this.newsType = newsType;
	}

}
