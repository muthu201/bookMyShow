package com.bookmyshow.bookMyShow.Dto;

import com.bookmyshow.bookMyShow.Entity.Theatre;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TheatreAdminDto {
	private int theatreAdminID;
	private String theatreAdminName;
	private Long theatreAdminContact;
	private String theatreAdminEmail;
	private Theatre adTheatre;

}
