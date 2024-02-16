package com.bookmyshow.bookMyShow.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Service.TheatreService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@RequestMapping("theatre")
@RestController
public class TheatreController {
	@Autowired
	TheatreService tService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre){
		return tService.saveTheatre(theatre);
	}
	@PutMapping("assignMoviesToTheatre")
	public ResponseEntity<ResponseStructure<Theatre>> assignMoviesToTheatre(@RequestParam int theatreId,@RequestBody List<Integer> movieIds) {
		return tService.assignMoviesToTheatre(theatreId, movieIds);
	}

	@GetMapping
	ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int theatreId){
		return tService.findTheatre(theatreId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestBody Theatre theatre,@RequestParam int theatreId){
		return tService.updateTheatre(theatre, theatreId);
	}
	@DeleteMapping
	ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int theatreId){
		return tService.findTheatre( theatreId);
	}
	@GetMapping("findAllTheatre")
	ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre(){
		return tService.findAllTheatre();
	}
}
