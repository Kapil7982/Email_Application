package com.email_app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class User {

	@Id
	private String email;

	@NotNull
	@Pattern(regexp = "[a-zA-Z]+", message = "First name must only contain letters.")
	private String firstName;

	@NotNull
	@Pattern(regexp = "[a-zA-Z]+", message = "Last name must only contain letters.")
	private String lastName;

	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must have exactly 10 digits.")
	private String mobileNumber;

	@NotNull
	@Past(message = "Date of birth should not be in future date")
	private LocalDate dateOfBirth;

	@NotNull
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$",
    message = "Password must be alphanumeric, contain 6-12 characters, and have at least one special character, one uppercase letter, one lowercase letter, and one digit.")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Email> emailList = new ArrayList<>();

}
