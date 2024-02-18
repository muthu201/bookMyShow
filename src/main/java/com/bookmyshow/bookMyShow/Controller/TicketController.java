package com.bookmyshow.bookMyShow.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Entity.Payment;
import com.bookmyshow.bookMyShow.Entity.PaymentType;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Entity.Ticket;
import com.bookmyshow.bookMyShow.Service.TicketService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("ticket")
@RestController
public class TicketController {
	@Autowired
	TicketService tService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Ticket>> saveTicket(@Valid @RequestBody Ticket ticket,BindingResult result){
		return tService.saveTicket(ticket);
	}
	@GetMapping
	ResponseEntity<ResponseStructure<Ticket>> findTicket(@RequestParam int ticketId){
		return tService.findTicket(ticketId);
	}
	@DeleteMapping("cancelBooking")
	public ResponseEntity<ResponseStructure<Payment>> cancelBooking(@RequestParam int ticketId,@RequestParam String userEmail,@RequestParam String userPassword){
		return tService.cancelBooking(ticketId, userEmail, userPassword);
	}
	@DeleteMapping
	ResponseEntity<ResponseStructure<Ticket>> deleteTicket(@RequestParam int ticketId){
		return tService.deleteTicket(ticketId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Ticket>> updateTicket(@Valid @RequestBody Ticket ticket,@RequestParam int ticketId,BindingResult result){
		return tService.updateTicket(ticket,ticketId);
	}
	@PostMapping("bookTicket")
	ResponseEntity<ResponseStructure<Ticket>> ticketBooking(@RequestParam String userEmail,@RequestParam String userPassword,@RequestParam int screenId,@RequestParam int movieId,@RequestParam SeatType seatType,@RequestBody List<Integer> seatIds,@RequestParam LocalDate bookingDate,@RequestParam PaymentType paymentType){
		return tService.ticketBooking(userEmail, userPassword, screenId, movieId, seatType, seatIds, bookingDate, paymentType);
	}
	@GetMapping("findAllTicket")
	ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket(){
		return tService.findAllTicket();
	}
}
