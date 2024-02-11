package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bookMyShow.Entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

}
