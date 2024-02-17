package com.bookmyshow.bookMyShow.Exception;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminNotFoundException(AdminNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("admin does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TheatreAdminNotFoundException(TheatreAdminNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("theatre admin does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> UserNotFoundException(UserNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("user does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MovieNotFoundException(MovieNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Movie does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SeatNotFoundException( SeatNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage(" Seat does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> PaymentNotFoundException( PaymentNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage(" Payment does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TheatreNotFoundException( TheatreNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage(" Theatre does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TicketNotFoundException( TicketNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage(" Ticket does not exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<Object>> constraintViolationException(ConstraintViolationException ex){
		ResponseStructure<Object> structure=new ResponseStructure<Object>();
		Map<String, String> hashMap=new HashMap<String, String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field=violation.getPropertyPath().toString();
			String message=violation.getMessage();
			hashMap.put(field, message);
		}
		structure.setMessage("add proper details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashMap);
		return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.FORBIDDEN);
	}
	
}
