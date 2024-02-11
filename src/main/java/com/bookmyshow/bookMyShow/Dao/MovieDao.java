package com.bookmyshow.bookMyShow.Dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Movie;
import com.bookmyshow.bookMyShow.Repo.MovieRepo;

@Repository
public class MovieDao {
	@Autowired
	MovieRepo mRepo;
	
	public Movie saveMovie(Movie movie) {
		return mRepo.save(movie);
	}
	
	public Movie findMovie(int movieId) {
		Optional<Movie> opMovie=mRepo.findById(movieId);
		if(opMovie.isPresent()) {
			return opMovie.get();
		}
		return null;
	}
	
	public Movie updateMovie(Movie movie,int movieId) {
		Movie newMovie=findMovie(movieId);
		if(newMovie != null) {
			movie.setMovieId(movieId);
			return mRepo.save(movie);
		}
		return null;
	}
	public Movie deleteMovie(int movieId) {
		Movie movie=findMovie(movieId);
		mRepo.delete(movie);
		return movie;
	}
	public List<Movie> findAllMovie() {
		return mRepo.findAll();
	}


		

}
