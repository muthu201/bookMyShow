package com.bookmyshow.bookMyShow.Exception;

public class PasswordWrongException extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public PasswordWrongException(String message) {
		this.message = message;
	}
	
}
