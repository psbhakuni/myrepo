package com.prakash.newsengine;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.prakash.newsengine.model.News;
import com.prakash.newsengine.model.NewsPriority;
import com.prakash.newsengine.model.NewsType;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/newsengine";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllNews(){
        System.out.println("Testing allNews API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> newsMap = restTemplate.getForObject(REST_SERVICE_URI+"/allNews", List.class);
         
        if(newsMap!=null){
            for(LinkedHashMap<String, Object> map : newsMap){
                System.out.println("News : id="+map.get("id")+", heading="+map.get("heading")+", content="+map.get("content")+", date="+map.get("date")
                +", NewsPriority="+map.get("priority")+", NewsType="+map.get("newsType"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void getBreakingNews(){
        System.out.println("Testing allNews API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> newsMap = restTemplate.getForObject(REST_SERVICE_URI+"/getBreakingNews", List.class);
         
        if(newsMap!=null){
            for(LinkedHashMap<String, Object> map : newsMap){
                System.out.println("News : id="+map.get("id")+", heading="+map.get("heading")+", content="+map.get("content")+", date="+map.get("date")
                +", NewsPriority="+map.get("priority")+", NewsType="+map.get("newsType"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET */
    private static void getNewsAtDateTime(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        News user = restTemplate.getForObject(REST_SERVICE_URI+"/findNews?timestamp=12/11/2017 07:06:08", News.class);
        System.out.println(user);
    }
    
    /* GET */
    private static void findNewsInRange(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<News> newsList = restTemplate.getForObject(REST_SERVICE_URI+"/findNewsInRange?start=12/11/2017 05:11:08&end=12/11/2017 10:08:08", List.class);
        System.out.println(newsList);
    }
     
    /* POST */
    private static void createNews(News news) {
        System.out.println("Testing create News API----------");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/postNews/", news, News.class);
        System.out.println("Added : "+news);
    }
 
    public static void main(String args[]){
    	listAllNews();
        createNews(new News(1,"News 1 heading"," News 1 content","12/11/2017 08:08:08", NewsPriority.NORMAL, NewsType.FINANCE));
        createNews(new News(1,"News 2 heading"," News 2 content","12/11/2017 05:11:08", NewsPriority.NORMAL, NewsType.GENERAL));
        createNews(new News(1,"News 3 heading"," News 3 content","12/11/2017 06:05:08", NewsPriority.BREAKING, NewsType.FINANCE));
        createNews(new News(1,"News 4 heading"," News 4 content","12/11/2017 07:06:08", NewsPriority.NORMAL, NewsType.SPORTS));
        createNews(new News(1,"News 5 heading"," News 5 content","12/11/2017 08:07:08", NewsPriority.BREAKING, NewsType.FINANCE));
        createNews(new News(1,"News 6 heading"," News 6 content","12/11/2017 10:08:08", NewsPriority.BREAKING, NewsType.FINANCE));
        listAllNews();
        getNewsAtDateTime();
        findNewsInRange();
    }
}