package com.prakash.newsengine.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.prakash.newsengine.model.News;
import com.prakash.newsengine.model.NewsPriority;
import com.prakash.newsengine.service.NewsService;
import com.prakash.newsengine.util.CustomErrorType;

@RestController
@RequestMapping("/newsengine")
public class NewsController {

	public static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	NewsService newsService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All News---------------------------------------------

	@RequestMapping(value = "/allNews", method = RequestMethod.GET)
	public ResponseEntity<List<News>> listAllNews(@RequestParam(value = "priority", required = false) NewsPriority priority) {
		List<News> newsList = new ArrayList<News>();
		if(NewsPriority.BREAKING.equals(priority)) {
			newsList = newsService.getBreakingNews();
		} else if(NewsPriority.ALL.equals(priority)) {
			newsList = newsService.getNews();
			newsList.addAll(newsService.getBreakingNews());
		} else{
			newsList = newsService.getNews();
		}
		if (newsList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postNews/", method = RequestMethod.POST)
	public ResponseEntity<String> postNews(@RequestBody News news, UriComponentsBuilder ucBuilder) {
		if (!NewsPriority.BREAKING.equals(news.getPriority()) && newsService.findNews(news) != null) {
			logger.error("Unable to create. A News with name {} already exist", news.getHeading());
			return new ResponseEntity<String>("Unable to create. News: '" + 
					news.getHeading() + "' already exist.",HttpStatus.CONFLICT);
		}
		newsService.postNews(news);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findNews", method = RequestMethod.GET)
	public ResponseEntity<?> getNews(@RequestParam("timestamp") String dateTime) {
		logger.info("Fetching News with Timestamp {}", dateTime);
		List<News> newsAtTime = newsService.getNews(dateTime);
		if (newsAtTime.isEmpty()) {
			logger.error("News at time '{}' not found.", dateTime);
			return new ResponseEntity(new CustomErrorType("News at time '" + dateTime 
					+ "' not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<News>>(newsAtTime, HttpStatus.OK);
	}

	@RequestMapping(value = "/findNewsInRange", method = RequestMethod.GET)
	public ResponseEntity<?> getNewsInRange(@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
		logger.info("Fetching news between {} and {}", startDate, endDate );
		List<News> newsAtTime = newsService.getNews(startDate, endDate);
		if (newsAtTime.isEmpty()) {
			logger.error("No news found between '{}' and '{}'.", startDate, endDate);
			return new ResponseEntity(new CustomErrorType("No news found between '"+startDate+"' and '"+endDate+"'."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<News>>(newsAtTime, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBreakingNews", method = RequestMethod.GET)
	public ResponseEntity<List<News>> getBreakingNews() {
		List<News> newsList = newsService.getBreakingNews();
		
		return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
	}
}