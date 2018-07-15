package com.prakash.newsengine.service;


import java.util.List;

import com.prakash.newsengine.model.News;

public interface NewsService {
	void postNews(News news);
	List<News> getNews();
	List<News> getNews(String date);
	List<News> getNews(String startDate, String endDate);
	
	boolean isNewsExist(News news);
	public News findNews(News newsToFind);
	public List<News> getBreakingNews();
}
