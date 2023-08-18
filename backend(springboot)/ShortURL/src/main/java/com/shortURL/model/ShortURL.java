package com.shortURL.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "shortURL_table")
public class ShortURL {
	
	@Id
	@GeneratedValue
	int urlId;
	
	@Column(unique = true, length = 500,nullable = false)
	@NotEmpty
	String longUrl;
	
	@Column(unique = true) 
	String shortUrl;
	
	//for every new link to be created this flag will be false by default
	@Column
	boolean isExpired = false;  
	
	public ShortURL() {
		
	}

	public ShortURL(String longUrl, String shortUrl) {
		super();
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	@Override
	public String toString() {
		return "ShortURL [urlId=" + urlId + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl + ", isExpired="
				+ isExpired + "]";
	}

	
	
	
	
}
