package com.prakash.newsengine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prakash.newsengine.model.News;
import com.prakash.newsengine.model.NewsPriority;

@Service("newsService")
public class NewsServiceImpl implements NewsService{
	public static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
	private static final int MAX_ENTRIES = 2;
	private static List<News> newsList;
	
	@SuppressWarnings("serial")
	LinkedHashMap<Long, News> breakingNewsMap = new LinkedHashMap<Long,
		      News>(MAX_ENTRIES + 1, .75F, false) {
				 @Override
		         protected boolean removeEldestEntry(Map.Entry<Long, News> eldest) {
		            return size() > MAX_ENTRIES;
		         }
		      };
	
    static{
    	newsList = new ArrayList<News>();
  	}
		      
	@Override
	public List<News> getNews() {
		return newsList;
	}

	@Override
	public void postNews(News news) {
		LOGGER.info("Creating news : {}", news);
		if(NewsPriority.BREAKING.equals(news.getPriority())){
			breakingNewsMap.put(Long.valueOf(breakingNewsMap.size()+(long)1), news);
		} else {
			
			if(!newsList.isEmpty()) {
				news.setId(newsList.get(newsList.size()-1).getId()+1);
			} else {
				news.setId(1);
			}
			
			newsList.add(news);
		}
	}
	
	public List<News> getBreakingNews(){
		 Set<Map.Entry<Long,News>> entrySet= breakingNewsMap.entrySet();
		 List<News> breakingNewsList = new ArrayList<News>();
		 
		 for (Iterator<Entry<Long, News>> iterator = entrySet.iterator(); iterator.hasNext(); ) {
			 Entry<Long, News> news = iterator.next();
			 breakingNewsList.add(news.getValue());
		}
		 
		return breakingNewsList;
	}
	
	@Override
	public List<News> getNews(String date) {
		List<News> newsListForTimeStamp = new ArrayList<>();
		for (Iterator<News> iterator = newsList.iterator(); iterator.hasNext(); ) {
		    News news = iterator.next();
			if (news.getDate().equals(date)) {
			    newsListForTimeStamp.add(news);
			}
		}
		return newsListForTimeStamp;
	}

	@Override
	public List<News> getNews(String startDate, String endDate) {
		List<News> newsListForTimeStamp = new ArrayList<>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS");
		try {
			for (Iterator<News> iterator = newsList.iterator(); iterator.hasNext(); ) {
			    News news = iterator.next();
			    
			    Date newsDate = sdf.parse(news.getDate());
				if (newsDate.compareTo(sdf.parse(startDate)) >=0 && newsDate.compareTo(sdf.parse(endDate)) <= 0) {
			        newsListForTimeStamp.add(news);
			    }
			}
		} catch (ParseException e) {
			LOGGER.debug("ERROR: {}", e.getMessage());
		}
		return newsListForTimeStamp;
	}
	
	
	public List<News> findByNewsText(String newsToken) {
		List<News> newsListContainingText = new ArrayList<News>();
		for(News news : newsList){
			if(news.getContent().contains(newsToken)){
				newsListContainingText.add(news);
			}
		}
		return newsListContainingText;
	}
	
	public News findNews(News newsToFind) {
		for(News news : newsList){
			if(news != null && news.getContent().equals(newsToFind.getContent()) && news.getDate().equals(newsToFind.getDate())){
				return news;
			}
		}
		return null;
	}
	
	public boolean isNewsExist(News news) {
		return findByNewsText(news.getHeading())!=null;
	}
	
	public void deleteAllNewss(){
		newsList.clear();
	}
}
