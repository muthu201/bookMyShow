package com.bookmyshow.bookMyShow.Entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	@NotNull(message = "movie name can not be null")
	@NotBlank(message = "movie name can not be blank")
	private String movieName;
	@Positive
	private int totalNoOfSeatsAvailable;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime showStartTime;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime showEndTime;
	@NotNull(message = "movie language can not be null")
	@NotBlank(message = "movie language can not be blank")
	private String movieLanguage; 
	@OneToMany
	private List<Seat> seatsList;
	
}
