package com.bookmyshow.bookMyShow.Controller;

import java.util.List;

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

import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Service.MovieService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("movie")
public class MovieController {
	@Autowired
	MovieService mService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie){
		return mService.saveMovie(movie);
	}

	@GetMapping
	ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int movieId){
		return mService.findMovie(movieId);
	}
	
	@DeleteMapping
	ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId){
		return mService.deleteMovie(movieId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody Movie movie,@RequestParam int movieId){
		return mService.updateMovie(movie, movieId);
	}
	@GetMapping("findAllMovie")
	ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie(){
		return mService.findAllMovie();
	}
	
}
