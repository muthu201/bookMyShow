package com.bookmyshow.bookMyShow.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.MovieDao;
import com.bookmyshow.bookMyShow.Dao.SeatDao;
import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Entity.SeatType;
import com.bookmyshow.bookMyShow.Exception.MovieNotFound;
import com.bookmyshow.bookMyShow.Exception.SeatNotFound;
import com.bookmyshow.bookMyShow.Repo.SeatRepo;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class MovieService {
	@Autowired
	MovieDao mDao;
	@Autowired
	SeatDao sDao;
	@Autowired
	SeatRepo sRepo;
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie) {
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		structure.setMessage("Movie save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(mDao.saveMovie(movie));
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId) {
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		Movie movie=mDao.findMovie(movieId);
		if(movie != null) {
			structure.setMessage("Movie found success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(movie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("movie not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId) {
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		Movie movie=mDao.findMovie(movieId);
		if(movie != null) {
			structure.setMessage("Movie delete success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(mDao.deleteMovie(movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie not deleted because,movie not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie,int movieId) {
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		Movie exMovie=mDao.updateMovie(movie,movieId);
		if(exMovie != null) {
			structure.setMessage("Movie update success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(exMovie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie not updated because,movie not found for the given id");
	}
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie() {
		ResponseStructure<List<Movie>> structure=new ResponseStructure<List<Movie>>();
		List<Movie> movies=mDao.findAllMovie();
			structure.setMessage("find all Movie success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(movies);
			return new ResponseEntity<ResponseStructure<List<Movie>>>(structure,HttpStatus.FOUND);
	}
	public List<Seat> findSeatsAvailability(int movieId,SeatType seatType) {
		Movie movie=mDao.findMovie(movieId);
		List<Seat> seatList=movie.getSeatList();
		List<Seat> seatsList=new ArrayList<Seat>();
		for (Seat seat : seatList) {
			if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
				seatsList.add(seat);
			}
		}
		return seatsList;
	}
	public  ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(int movieId,SeatType seatType) {
		Movie movie=mDao.findMovie(movieId);
		List<Seat> seatList=movie.getSeatList();
		List<Seat> seatsList=new ArrayList<Seat>();
		for (Seat seat : seatList) {
			if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
				seatsList.add(seat);
			}
		}
		ResponseStructure<List<Seat>> structure=new ResponseStructure<List<Seat>>();
		structure.setMessage("find seat availability found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(seatsList);
		return new ResponseEntity<ResponseStructure<List<Seat>>>(structure,HttpStatus.FOUND);
	}
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(int movieId,List<Integer> seatIds) {
		Movie movie=mDao.findMovie(movieId);
		if(movie != null) {
		List<Seat> seats=sRepo.findAllById(seatIds);
			if(seats != null) {
				movie.setSeatList(seats);
				ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
				structure.setMessage("assign seats to Movie success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(mDao.updateMovie(movie,movie.getMovieId()));
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
			}
			else {
				throw new SeatNotFound("we can't assign seats to movies because,seats not found for set of seatids");
			}
		}
		 throw new MovieNotFound("we can't assign seats to movies because,movie not found for the given id");
	}

}
