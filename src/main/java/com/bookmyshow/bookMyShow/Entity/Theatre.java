package com.bookmyshow.bookMyShow.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Theatre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	@NotNull(message = "theatre name can not be null")
	@NotBlank(message = "theatre name can not be blank")
	private String theatreName;
	@NotNull(message = "theatre Location can not be null")
	@NotBlank(message = "theatre Location can not be blank")
	private String theatreLocation;
	@Positive
	@Max(value = 10)
	private int totalNoOfSeats;
	@OneToMany
	List<Screen> theatreScreensList;
	
}
