package com.bookmyshow.bookMyShow.Dto;

import java.util.List;

import com.bookmyshow.bookMyShow.Entity.Theatre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	private int adminId;
	private String adminName;
	private String adminEmail;
	private String adminContact;
	List<Theatre> theatresList;

}
