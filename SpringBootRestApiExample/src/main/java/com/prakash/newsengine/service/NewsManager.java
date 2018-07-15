package com.prakash.newsengine.service;

import java.util.List;

import com.prakash.newsengine.model.News;

public interface NewsManager {
	public int addNews(News news);
	public List<News> getAllNews();
}
