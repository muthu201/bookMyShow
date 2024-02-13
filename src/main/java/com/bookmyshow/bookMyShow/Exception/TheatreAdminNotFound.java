package com.bookmyshow.bookMyShow.Exception;

public class TheatreAdminNotFound extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}
	public TheatreAdminNotFound(String message) {
		this.message = message;
	}
}
