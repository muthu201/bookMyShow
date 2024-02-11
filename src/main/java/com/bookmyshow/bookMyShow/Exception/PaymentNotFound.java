package com.bookmyshow.bookMyShow.Exception;

public class PaymentNotFound extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public PaymentNotFound(String message) {
		this.message = message;
	}
	
}
