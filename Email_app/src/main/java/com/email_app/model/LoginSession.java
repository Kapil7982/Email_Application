package com.email_app.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class LoginSession {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Email
	@NotNull
	private String email;

	@NotNull
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$",
    message = "Password must be alphanumeric, contain 6-12 characters, and have at least one special character, one uppercase letter, one lowercase letter, and one digit.")
	private String password;

	@JsonIgnore
	private LocalDateTime date;
	
	@JsonIgnore
	private String loginKey;

}
