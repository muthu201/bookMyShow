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
import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Service.MovieService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("movie")
public class MovieController {
	@Autowired
	MovieService mService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Movie>> saveMovie(@Valid @RequestBody Movie movie,BindingResult result){
		return mService.saveMovie(movie);
	}

	@GetMapping
	ResponseEntity<ResponseStructure<Movie>> findMovie( @RequestParam int movieId){
		return mService.findMovie(movieId);
	}
	@PutMapping("assignSeatsToMovies")
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(@RequestParam int movieId,@RequestBody List<Integer> seatIds) {
		return mService.assignSeatsToMovies(movieId, seatIds);
	}

	@DeleteMapping
	ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId){
		return mService.deleteMovie(movieId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Movie>> updateMovie(@Valid @RequestBody Movie movie,@RequestParam int movieId,BindingResult result){
		return mService.updateMovie(movie, movieId);
	}
	@GetMapping("findAllMovie")
	ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie(){
		return mService.findAllMovie();
	}
	@GetMapping("findSeatAvailability")
	ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(@RequestParam int movieId,@RequestParam SeatType seatType) {
		return mService.findSeatAvailability(movieId, seatType);
	}
}
