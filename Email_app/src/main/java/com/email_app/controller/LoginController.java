package com.email_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.email_app.exception.LoginException;
import com.email_app.model.LoginSession;
import com.email_app.service.LoginSessionService;

@RestController
public class LoginController {

	@Autowired
	private LoginSessionService loginSessionService;

	@PostMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody LoginSession loginSession) throws LoginException {
		return new ResponseEntity<String>(loginSessionService.login(loginSession), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam("email") String email, @RequestParam("loginKey") String loginKey)
			throws LoginException {
		return new ResponseEntity<String>(loginSessionService.logout(email, loginKey), HttpStatus.OK);
	}

}
