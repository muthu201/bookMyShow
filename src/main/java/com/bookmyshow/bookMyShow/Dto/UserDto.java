package com.bookmyshow.bookMyShow.Dto;

import com.bookmyshow.bookMyShow.Entity.Ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private int userId;
	private String userName;
	private long userContact;
	private String userEmail;
	private Ticket ticket;

}
