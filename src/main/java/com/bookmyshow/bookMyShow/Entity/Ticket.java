package com.bookmyshow.bookMyShow.Entity;

import java.time.LocalDate;
import java.util.HashSet;

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
	private LocalDate bookingDate;
	private double totalTicketPrice;
	@ManyToOne
	private User userTickets;
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movieTicket; 
	@ManyToMany(cascade = CascadeType.ALL)
	private HashSet<Seat> ticketSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private Payment ticketPayment;

}
