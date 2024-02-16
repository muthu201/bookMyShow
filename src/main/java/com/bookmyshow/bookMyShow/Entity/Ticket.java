package com.bookmyshow.bookMyShow.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private int movieId;
	private String movieName;
	private LocalTime movieStartTime;
	private LocalTime moviesEndTime;
	private String movieLanguage; 
	private LocalDate bookingDate;
	private double totalTicketPrice; 
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Seat> ticketSeats;
	@ManyToOne(cascade = CascadeType.ALL)
	private Payment ticketPayment;

}
