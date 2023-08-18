package com.shortURL.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortURL.customException.CustomException;
import com.shortURL.dto.UrlDto;
import com.shortURL.model.ShortURL;
import com.shortURL.service.URLService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class URLController {

	@Autowired
	URLService service;

	// I used syso for debugging purpose hence commented out

	@PostMapping("/create")
	public ResponseEntity<?> createShortURl(@Valid @RequestBody UrlDto reqObj) {
		// System.out.println("in create method");
		// System.out.println(reqObj);
		ShortURL justCreatedShorURL;
		try {
			justCreatedShorURL = service.createShortURL(reqObj.getLongURl());
			System.out.println(justCreatedShorURL);
			return new ResponseEntity<ShortURL>(justCreatedShorURL, HttpStatus.OK);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("in catch block");
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> redirectToOriginalURL(@PathVariable String shortUrl)
			throws CustomException, URISyntaxException {
		// System.out.println("in getmapping");
		ShortURL obj = service.getShortURLObject(shortUrl);

		if (obj.isExpired() == false) {
			URI uri = new URI(obj.getLongUrl());
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setLocation(uri);
			return new ResponseEntity<>(httpHeader, HttpStatus.MOVED_PERMANENTLY);
		} else {
			throw new CustomException("link expired");
		}

	}

	@PostMapping("/search")
	public ResponseEntity<ShortURL> getShortURL(@RequestBody UrlDto reqObj) {
		ShortURL searchShortURL = service.getURLObjectByLong(reqObj.getLongURl());
		// System.out.println("in search ");
		// System.out.println(searchShortURL);
		return new ResponseEntity<>(searchShortURL, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> deleteURL(@RequestBody UrlDto reqObj) throws IllegalArgumentException {
		service.updateURL(reqObj.getLongURl());
		return new ResponseEntity<>("updated sucessfully", HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(Exception e) throws IOException, URISyntaxException {

		Map<String, String> errorResponse = Map.of("message", e.getLocalizedMessage(), "status",
				HttpStatus.INTERNAL_SERVER_ERROR.toString());

		if (e instanceof NoSuchElementException) {
			String angularURL = "http://localhost:4200/noSuchShortLink";
			URI uri = new URI(angularURL);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setLocation(uri);
			return new ResponseEntity<>(httpHeader, HttpStatus.MOVED_PERMANENTLY);
		}

		if (e instanceof CustomException) {
			// System.out.println("in cusexp ins");
			String angularURL = "http://localhost:4200/linkExpired";
			URI uri = new URI(angularURL);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setLocation(uri);
			return new ResponseEntity<>(httpHeader, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
