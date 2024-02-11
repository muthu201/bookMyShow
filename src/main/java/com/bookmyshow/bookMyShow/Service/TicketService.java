package com.bookmyshow.bookMyShow.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.TicketDao;
import com.bookmyshow.bookMyShow.Entity.Ticket;
import com.bookmyshow.bookMyShow.Exception.TicketNotFound;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class TicketService {
	@Autowired
	TicketDao tDao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket) {
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("Ticket save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(tDao.saveTicket(ticket));
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int ticketId) {
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket ticket=tDao.findTicket(ticketId);
		if(ticket != null) {
		structure.setMessage("Ticket found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(ticket);
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.FOUND);
		}
		throw new TicketNotFound("ticket not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int ticketId) {
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket ticket=tDao.findTicket(ticketId);
		if(ticket != null) {
		structure.setMessage("Ticket Delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.deleteTicket(ticketId));
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket not deleted because,ticket not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket,int ticketId) {
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket newTicket=tDao.updateTicket(ticket,ticketId);
		if(newTicket != null) {
		structure.setMessage("Ticket update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(newTicket);
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket not updated because,ticket not found for the given id");
	}
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket() {
		ResponseStructure<List<Ticket>> structure=new ResponseStructure<List<Ticket>>();
		structure.setMessage("find all Ticket success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(tDao.findAllTicket());
		return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure,HttpStatus.FOUND);
	}
}
