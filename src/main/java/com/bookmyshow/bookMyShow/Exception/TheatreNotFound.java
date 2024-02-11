package com.bookmyshow.bookMyShow.Exception;

public class TheatreNotFound extends RuntimeException{
	String message;
	
	public TheatreNotFound(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
