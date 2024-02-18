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

import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Entity.Screen;
import com.bookmyshow.bookMyShow.Service.ScreenService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("screen")
@RestController
public class ScreenController {
	@Autowired
	ScreenService sService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(@Valid @RequestBody Screen screen,BindingResult result) {
		return sService.saveScreen(screen);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Screen>> findScreen( @RequestParam int  screenId) {
		return sService.findScreen(screenId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(@RequestParam int screenId) {
		return sService.deleteScreen(screenId);
	}
	@PutMapping("assignMoviesToScreen")
	public ResponseEntity<ResponseStructure<Screen>> assignMoviesToScreen(@RequestParam int screenId,@RequestBody List<Integer> movieIds) {
		return sService.assignMoviesToScreen(screenId, movieIds);
	}
	
	@DeleteMapping("deleteMovieOrShowInScreen")
	public ResponseEntity<ResponseStructure<Screen>> deleteMovieOrShowInScreen(@RequestParam int screenId,@RequestParam int movieId) {
		return sService.deleteMovieOrShowInScreen(screenId, movieId);
	}


	@PutMapping
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(@Valid @RequestBody Screen screen,@RequestParam int screenId,BindingResult result) {
		return sService.updateScreen(screen, screenId);
	}
	
	@GetMapping("findAllScreen")
	public ResponseEntity<ResponseStructure<List<Screen>>> findAllScreen() {
		return sService.findAllScreen();
	}

}
