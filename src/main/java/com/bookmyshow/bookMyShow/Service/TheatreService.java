package com.bookmyshow.bookMyShow.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bookMyShow.Dao.MovieDao;
import com.bookmyshow.bookMyShow.Dao.TheatreDao;
import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Entity.Screen;
import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Exception.TheatreNotFound;
import com.bookmyshow.bookMyShow.Repo.MovieRepo;
import com.bookmyshow.bookMyShow.Repo.ScreenRepo;
import com.bookmyshow.bookMyShow.Repo.SeatRepo;
import com.bookmyshow.bookMyShow.Repo.TheatreRepo;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class TheatreService {
	@Autowired
	TheatreDao tDao;
	@Autowired
	TheatreRepo tRepo;
	@Autowired
	ScreenRepo sRepo;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre) {
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("theatre save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(tDao.saveTheatre(theatre));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId) {
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		Theatre theatre=tDao.findTheatre(theatreId);
		if(theatre != null) {
		structure.setMessage("theatre found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(theatre);
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
	}
		throw new TheatreNotFound("theatre not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId) {
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		Theatre theatre=tDao.findTheatre(theatreId);
		if(theatre != null) {
		structure.setMessage("theatre delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.deleteTheatre(theatreId));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
	}
		throw new TheatreNotFound("theatre not deleted because,theatre not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre,int theatreId) {
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		Theatre exTheatre=tDao.updateTheatre(theatre, theatreId);
		if(exTheatre != null) {
		structure.setMessage("theatre update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(exTheatre);
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
	}
		throw new TheatreNotFound("theatre not updated because,theatre not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Theatre>> assignScreensToTheatre(int theatreId,List<Integer> screenIds) {
		Theatre theatre=tDao.findTheatre(theatreId);
		if(theatre != null) {
		List<Screen> exScreens=sRepo.findAllById(screenIds);
		theatre.setTheatreScreensList(exScreens);
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("assign screen to theatre success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.updateTheatre(theatre, theatreId));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("we can't assign movie to the theatre because, theatre not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre() {
		ResponseStructure<List<Theatre>> structure=new ResponseStructure<List<Theatre>>();
		structure.setMessage("find all theatre success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(tDao.findAllTheatre());
		return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure,HttpStatus.FOUND);
	}
}
