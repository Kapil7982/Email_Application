package com.email_app.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.email_app.exception.EmailException;
import com.email_app.exception.LoginException;
import com.email_app.exception.UserException;
import com.email_app.model.Email;
import com.email_app.model.User;
import com.email_app.model.UserDTO;
import com.email_app.model.UserUpdateDTO;
import com.email_app.service.UserService;

@RestController
@RequestMapping("/masaimail")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
		return new ResponseEntity<User>(userService.register(userDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mail")
	public ResponseEntity<List<Email>> checkAllMail(@Valid @RequestParam("userEmail") String userEmail)
			throws UserException, EmailException, LoginException {
		return new ResponseEntity<List<Email>>(userService.checkAllEmails(userEmail), HttpStatus.OK);
	}

	@GetMapping("/starred")
	public ResponseEntity<List<Email>> checkAllStarredMail(@Valid @RequestParam("userEmail") String userEmail)
			throws UserException, EmailException, LoginException {
		return new ResponseEntity<List<Email>>(userService.checkAllStarredEmails(userEmail), HttpStatus.OK);
	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateDTO userDTO,
			@RequestParam("userEmail") String userEmail) throws UserException, LoginException {
		return new ResponseEntity<User>(userService.updateUser(userDTO, userEmail), HttpStatus.OK);
	}

}
