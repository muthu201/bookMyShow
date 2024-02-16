package com.bookmyshow.bookMyShow.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.MovieDao;
import com.bookmyshow.bookMyShow.Dao.PaymentDao;
import com.bookmyshow.bookMyShow.Dao.SeatDao;
import com.bookmyshow.bookMyShow.Dao.TicketDao;
import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Entity.Payment;
import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Entity.Ticket;
import com.bookmyshow.bookMyShow.Entity.User;
import com.bookmyshow.bookMyShow.Exception.SeatNotFound;
import com.bookmyshow.bookMyShow.Exception.TicketNotFound;
import com.bookmyshow.bookMyShow.Repo.SeatRepo;
import com.bookmyshow.bookMyShow.Repo.UserRepo;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class TicketService {
	@Autowired
	UserRepo uDao;
	@Autowired
	TicketDao tDao;
	@Autowired
	SeatDao sDao;
	@Autowired
	MovieService mService;
	@Autowired
	SeatRepo sRepo;
	@Autowired
	PaymentDao pDao;
	@Autowired
	MovieDao mDao;
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
	public List<Seat> bookSeat(List<Seat> availableSeats, List<Integer> seatIds, int movieId){
		List<Seat> seats=new ArrayList<Seat>();
		for (Seat seatAvail : availableSeats) {
			for (Integer integer : seatIds) {
				if(seatAvail.getSeatId()==integer) {
					seats.add(seatAvail);
					Movie movie=mDao.findMovie(movieId);
					movie.setTotalNoSeats(movie.getTotalNoSeats()-1);
					mDao.updateMovie(movie, movieId);
					seatAvail.setSeatAvailability(false);
					sDao.updateSeat(seatAvail, seatAvail.getSeatId());
				}
			}
		}
		return seats;
	}
	public ResponseEntity<ResponseStructure<Ticket>> ticketBooking(String userEmail,String userPassword,int movieId,SeatType seatType,List<Integer> seatIds,LocalDate bookingDate){
		
		Ticket ticket=new Ticket();
		List<Seat> availableSeat=mService.findSeatsAvailability(movieId, seatType);
		if(availableSeat != null) {
		List<Seat> bookedSeats=bookSeat(availableSeat, seatIds,movieId);
		if(!bookedSeats.isEmpty()) {
		Payment payment= processPayement(bookedSeats,bookingDate);
		ticket.setBookingDate(bookingDate);
		Movie movie=mDao.findMovie(movieId);
		ticket.setMovieId(movieId);
		ticket.setMovieLanguage(movie.getMovieLanguage());
		ticket.setMovieName(movie.getMovieName());
		ticket.setMovieStartTime(movie.getMovieStartTime());
		ticket.setMoviesEndTime(movie.getMoviesEndTime());
		ticket.setTicketPayment(payment);
		ticket.setTicketSeats(bookedSeats);
		ticket.setTotalTicketPrice(payment.getPrice());
		Ticket newTicket=tDao.saveTicket(ticket);
		
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("ticket booked successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(newTicket);
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.CREATED);
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");

		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");
		
	}
	public ResponseEntity<ResponseStructure<Payment>> cancelBooking(int ticketId){
		Ticket ticket=tDao.findTicket(ticketId);
		if(ticket != null) {
			List<Seat> lists = ticket.getTicketSeats();
			for (Seat seat : lists) {
				seat.setSeatAvailability(true);
				sDao.updateSeat(seat, seat.getSeatId());
				Movie movie=mDao.findMovie(ticket.getMovieId());
				movie.setTotalNoSeats(movie.getTotalNoSeats()+1);
				mDao.updateMovie(movie, ticket.getMovieId());
			}
			ticket.setTicketSeats(null);
			Payment payment=ticket.getTicketPayment();
			tDao.deleteTicket(ticketId);
			ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
			structure.setMessage("cancel booking success.amount refunded");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>> (structure,HttpStatus.OK);
		}
		else {
			throw new TicketNotFound("ticket not found for given id");
		}
	}
	private User userLogin(String userEmail, String userPassword) {
		User user=uDao.findByUserEmail(userEmail);
		if(user != null) {
		  if(user.getUserPassword().equals(userPassword)) {
			  return user;
		  }
		  return null;
		}
		return null;
	}
	private Payment processPayement(List<Seat> bookedSeats,LocalDate bookingDate) {
		Payment payment=new Payment();
		long amount=0;
		for (Seat seat : bookedSeats) {
			if(seat.getSeatType()==SeatType.premium) {
				amount+=150;
			}
			else if(seat.getSeatType()==SeatType.vip) {
				amount+=110;
			}
			else {
				amount+=60;
			}
		}
		payment.setPaymentDate(bookingDate);
		payment.setPrice(amount);
		Payment newPayment=pDao.savePayment(payment);
		return newPayment;
	}
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket() {
		ResponseStructure<List<Ticket>> structure=new ResponseStructure<List<Ticket>>();
		structure.setMessage("find all Ticket success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(tDao.findAllTicket());
		return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure,HttpStatus.FOUND);
	}
}
