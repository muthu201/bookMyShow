package com.bookmyshow.bookMyShow.Entity;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private ScreenNumber screenNumber;
	@Positive
	private int movieId;
	@NotNull(message = "movie name can not be null")
	@NotBlank(message = "movie name can not be blank")	
	private String movieName;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime showStartTime;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime showEndTime;
	@NotNull(message = "movie language can not be null")
	@NotBlank(message = "movie language can not be blank")
	private String movieLanguage; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	@Positive
	private double totalTicketPrice; 
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seat> ticketSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private Payment ticketPayment;

}
