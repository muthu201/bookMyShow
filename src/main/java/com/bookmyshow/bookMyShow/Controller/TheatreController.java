package com.bookmyshow.bookMyShow.Controller;

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

import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Service.TheatreService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("theatre")
@RestController
public class TheatreController {
	@Autowired
	TheatreService tService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@Valid @RequestBody Theatre theatre,BindingResult result){
		return tService.saveTheatre(theatre);
	}
	@PutMapping("assignScreensToTheatre")
	public ResponseEntity<ResponseStructure<Theatre>> assignScreensToTheatre(@RequestParam int theatreId,@RequestBody List<Integer> screenIds) {
		return tService.assignScreensToTheatre(theatreId, screenIds);
	}

	@GetMapping
	ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int theatreId){
		return tService.findTheatre(theatreId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@Valid @RequestBody Theatre theatre,@RequestParam int theatreId,BindingResult result){
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
