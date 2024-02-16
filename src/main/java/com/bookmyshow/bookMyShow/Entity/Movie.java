package com.bookmyshow.bookMyShow.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	private int totalNoSeats;
	private LocalDate movieReleaseDate;
	private LocalTime movieStartTime;
	private LocalTime moviesEndTime;
	private String movieLanguage; 
	@OneToMany
	List<Seat> seatList;

}
