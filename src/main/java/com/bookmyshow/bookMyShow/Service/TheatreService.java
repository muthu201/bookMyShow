package com.bookmyshow.bookMyShow.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.TheatreDao;
import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Exception.TheatreNotFound;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class TheatreService {
	@Autowired
	TheatreDao tDao;
	
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
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre() {
		ResponseStructure<List<Theatre>> structure=new ResponseStructure<List<Theatre>>();
		structure.setMessage("find all theatre success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(tDao.findAllTheatre());
		return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure,HttpStatus.FOUND);
	}
}
