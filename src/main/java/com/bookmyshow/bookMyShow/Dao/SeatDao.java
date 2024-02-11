package com.bookmyshow.bookMyShow.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Repo.SeatRepo;

@Repository
public class SeatDao {
	
	@Autowired
	SeatRepo sRepo;
	
	public Seat saveSeat(Seat seat){
		return sRepo.save(seat);
	}
	public Seat findSeat(int seatId){
		Optional<Seat> opSeat=sRepo.findById(seatId);
		if(opSeat.isPresent()) {
			return opSeat.get();
		}
		return null;
	}
	public Seat deleteSeat(int seatId){
		Seat seat=findSeat(seatId);
		sRepo.delete(seat);
		return seat;
	}
	public Seat updateSeat(Seat seat,int seatId){
		Seat newSeat=findSeat(seatId);
		if(newSeat != null) {
			seat.setSeatId(seatId);
			return sRepo.save(seat);
		}
		return null;
	}
	public List<Seat> findAllSeat() {
		return sRepo.findAll();
	}
	

}
