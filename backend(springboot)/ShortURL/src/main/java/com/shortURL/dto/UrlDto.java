package com.shortURL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UrlDto {
	
	@NotEmpty
	@NotNull
	@NotBlank
	String longURl;
	
	public UrlDto() {
		
	}

	public UrlDto(String longURl) {
		super();
		this.longURl = longURl;
	}

	public String getLongURl() {
		return longURl;
	}

	public void setLongURl(String longURl) {
		this.longURl = longURl;
	}

	@Override
	public String toString() {
		return "UrlDto [longURl=" + longURl + "]";
	}
	
	

}
