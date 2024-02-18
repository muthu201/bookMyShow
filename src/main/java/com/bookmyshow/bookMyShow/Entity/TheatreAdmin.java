package com.bookmyshow.bookMyShow.Entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class TheatreAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminID;
	@NotNull(message = "theatre admin name can not be null")
	@NotBlank(message = "theatre admin name can not be blank")
	private String theatreAdminName;
	@Positive
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private Long theatreAdminContact;
	@Email
	private String theatreAdminEmail;
	@NotNull(message = "admin password can not be null")
	@NotBlank(message = "admin password can not be blank")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,20}$",message = "password must be min 4 and max 20 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String theatreAdminPassword;
	@OneToOne
	private Theatre adTheatre;
	
}
