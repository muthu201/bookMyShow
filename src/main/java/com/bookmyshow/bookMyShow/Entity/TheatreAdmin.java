package com.bookmyshow.bookMyShow.Entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class TheatreAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminID;
	private String theatreAdminName;
	private Long theatreAdminContact;
	private String theatreAdminEmail;
	private String theatreAdminPassword;
	@OneToOne
	private Theatre adTheatre;
	
}
