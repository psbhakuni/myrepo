package com.prakash.newsengine.model;

import java.io.Serializable;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String heading;
	private String content;
	private String date;
	private NewsPriority priority;
	private NewsType newsType;
	
	public News() {
	}
	
	public News(long id, String heading, String content, String date, NewsPriority priority, NewsType newsType) {
		super();
		this.id = id;
		this.heading = heading;
		this.content = content;
		this.date = date;
		this.priority = priority;
		this.newsType = newsType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public NewsPriority getPriority() {
		return priority;
	}
	public void setPriority(NewsPriority priority) {
		this.priority = priority;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((heading == null) ? 0 : heading.hashCode());
		result = prime * result + ((newsType == null) ? 0 : newsType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (heading == null) {
			if (other.heading != null) {
				return false;
			}
		} else if (!heading.equals(other.heading)) {
			return false;
		}
		return newsType == other.newsType ;
	}
	
	@Override
	public String toString() {
		return "News{" +
				"id=" + id +
				", heading='" + heading + '\'' +
				", content='" + content + '\'' +
				", date='" + date + '\'' +
				", priority=" + priority +
				", newsType=" + newsType +
				'}';
	}
}
