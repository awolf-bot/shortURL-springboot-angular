package com.shortURL.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortURL.model.ShortURL;

@Repository
public interface URLDao extends JpaRepository<ShortURL, Integer> {

	Optional<ShortURL> findByShortUrl(String shortUrl);
	
	Optional<ShortURL> findByLongUrl(String longUrl);
	

}
