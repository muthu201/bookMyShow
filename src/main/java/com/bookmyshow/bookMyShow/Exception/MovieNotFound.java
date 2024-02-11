package com.bookmyshow.bookMyShow.Exception;

public class MovieNotFound extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public MovieNotFound(String message) {
		this.message = message;
	}
}
