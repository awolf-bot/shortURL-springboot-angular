package com.shortURL.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortURL.dao.URLDao;
import com.shortURL.model.ShortURL;

@Service
public class URLService {
	
	@Autowired
	URLDao dao;
	
	Random rm = new Random();
			
	
	public URLService() {
		
	}
	
	public ShortURL createShortURL(String longURL) throws SQLIntegrityConstraintViolationException{
		
		String shortURL = "http://localhost:8080/short" + rm.nextInt(); 
		ShortURL newShortURL = new ShortURL(longURL,shortURL);
		
		try {
			dao.save(newShortURL);
			return newShortURL;
		}catch (Exception e) {
			throw new SQLIntegrityConstraintViolationException("this url already exsists");
		}
	}

	public ShortURL getShortURLObject(String shortUrl) throws NoSuchElementException{
		String completeShortURL = "http://localhost:8080/" + shortUrl;
		//System.out.println(shortUrl);
		return dao.findByShortUrl(completeShortURL).get();
	}

	public void updateURL(String longUrl) throws IllegalArgumentException{
		ShortURL toUpdate = dao.findByLongUrl(longUrl).get();
		toUpdate.setExpired(true); 
		dao.save(toUpdate);
		//System.out.println("link set to expire");
		
	}

	public String getShortURLValue(String longUrl) {
		ShortURL urlObj = dao.findByLongUrl(longUrl).get();
		return urlObj.getShortUrl();
	}
	
	public ShortURL getURLObjectByLong(String longUrl) {
		return dao.findByLongUrl(longUrl).get();
	}
}
