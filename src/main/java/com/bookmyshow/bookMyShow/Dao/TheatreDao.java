package com.bookmyshow.bookMyShow.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Repo.TheatreRepo;

@Repository
public class TheatreDao {
	@Autowired
	TheatreRepo tRepo;
	
	public Theatre saveTheatre(Theatre theatre) {
		return tRepo.save(theatre);
	}
	public Theatre findTheatre(int theatreId) {
		Optional<Theatre> opTheatre=tRepo.findById(theatreId);
		if(opTheatre.isPresent()) {
			return opTheatre.get();
		}
		return null;
	}
	public Theatre updateTheatre(Theatre theatre,int theatreId) {
		Theatre newTheatre=findTheatre(theatreId);
		if(newTheatre != null) {
			theatre.setTheatreId(theatreId);
			return tRepo.save(theatre);
		}
		return null;
	}
	public Theatre deleteTheatre(int theatreId) {
		Theatre theatre=findTheatre(theatreId);
		tRepo.delete(theatre);
		return theatre;
	}
	public List<Theatre> findAllTheatre() {
		return tRepo.findAll();
	}

	

	
}
