package com.bookmyshow.bookMyShow.Exception;

public class ScreenNotFound extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public ScreenNotFound(String message) {
		this.message = message;
	}
	
}
