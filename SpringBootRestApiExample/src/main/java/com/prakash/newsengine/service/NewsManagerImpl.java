package com.prakash.newsengine.service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prakash.newsengine.model.News;
@Component
public class NewsManagerImpl implements NewsManager {

	public static final Logger LOGGER = LoggerFactory.getLogger(NewsManagerImpl.class);
	static final String DATA_STORE = "myObjects.dat";
	static int count = 0;
	{
		File file = new File(DATA_STORE);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
			file.createNewFile();
		} catch (IOException e) {
			LOGGER.info("Error while setting up new file..");
		}
	}
	
	@Override
	public int addNews(News news) {
		LOGGER.info("Creating News : {}",news);
		
		ObjectOutputStream oos =null;
	    try {
	    	FileOutputStream fos = new FileOutputStream(DATA_STORE,true);
	    	oos = new ObjectOutputStream(fos);

	    	oos.writeObject(news);
	        count = count+1;
	        oos.flush();
			oos.close();
	        LOGGER.info("Created News : {}", news);
	    } catch (Exception e) {
				LOGGER.debug("Error: {}",e.getMessage());
		} finally {
			try {
				oos.flush();
				oos.close();
			} catch (Exception e) {
				LOGGER.debug("Error while closingStream: {}",e.getMessage());
			}
			try {
				oos.close();
			} catch (Exception e) {
				LOGGER.debug("Error while closingStream: {}",e.getMessage());
			}
		}
		
		return 1;
	}
	
	@Override
	public List<News> getAllNews() {
		ObjectInputStream ois = null;
		List<News> newsList = new ArrayList<News>();
		try {
			FileInputStream fis = new FileInputStream(DATA_STORE);
			ois = new ObjectInputStream(fis);
			boolean recoedFound = true;
			while(recoedFound) {
				News news = null;
				try {
					news = (News) ois.readObject();
				}catch (EOFException eof) {
					recoedFound = false;
					LOGGER.debug("Reached end of file");
		        }
				newsList.add(news);
			}
		}catch (EOFException eof) {
			LOGGER.debug("Reached end of file");
        } catch (Exception e) {
			LOGGER.debug("Error: "+e.getMessage());
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
				LOGGER.debug("Error while closing ObjectOutputStream: "+e.getMessage());
			}
		}
		LOGGER.info("All news : {}",newsList);
		return newsList;
	}
	
}
