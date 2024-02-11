package com.bookmyshow.bookMyShow.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Ticket;
import com.bookmyshow.bookMyShow.Repo.TicketRepo;

@Repository
public class TicketDao {
	@Autowired
	TicketRepo tRepo;
	
	public Ticket saveTicket(Ticket ticket) {
		return tRepo.save(ticket);
	}
	public Ticket findTicket(int ticketId) {
		Optional<Ticket> opTicket=tRepo.findById(ticketId);
		if(opTicket.isPresent()){
			return opTicket.get();
		}
		return null;
	}
	public Ticket updateTicket(Ticket ticket,int ticketId) {
		Ticket newTicket=findTicket(ticketId);
		if(newTicket != null) {
			ticket.setTicketId(ticketId);
			return tRepo.save(ticket);
		}
		return null;
	}
	public Ticket deleteTicket(int ticketId) {
		Ticket ticket=findTicket(ticketId);
		tRepo.delete(ticket);
		return ticket;
	}
	public List<Ticket> findAllTicket() {
		return tRepo.findAll();
	}



}
