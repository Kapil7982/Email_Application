package com.email_app.service;

import org.springframework.stereotype.Service;
import com.email_app.exception.LoginException;
import com.email_app.model.LoginSession;
import com.email_app.model.User;
import com.email_app.repo.LoginSessionRepo;
import com.email_app.repo.UserRepo;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import net.bytebuddy.utility.RandomString;

@Service
public class LoginSessionServiceImpl implements LoginSessionService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LoginSessionRepo loginSessionRepo;

	@Override
	public String login(LoginSession loginSession) throws LoginException {

		User user = userRepo.findByEmail(loginSession.getEmail());
		
		if (user == null)
			
			throw new LoginException("Email id not found!");

		if (user.getPassword().equals(loginSession.getPassword())) {

			LoginSession ls = loginSessionRepo.findByEmail(loginSession.getEmail());
			if (ls != null)
				throw new LoginException("User already logged in successfully !!");

			LoginSession lsd = new LoginSession();
			lsd.setEmail(loginSession.getEmail());
			lsd.setPassword(loginSession.getPassword());
			lsd.setDate(LocalDateTime.now());

			String key = RandomString.make(6);
			lsd.setLoginKey(key);
			loginSessionRepo.save(lsd);

			return "Login Sucessufull! ( key - " + key + " )";

		} else {
			throw new LoginException("Please enter the valid password");
		}
	}

	@Override
	public String logout(String email, String userkey) throws LoginException {

		User user = userRepo.findByEmail(email);
		
		if (user == null)
			throw new LoginException("Please enter the valid email Id");

		LoginSession ls = loginSessionRepo.findByEmail(email);
		if (ls == null)
			throw new LoginException("Please login first!");

		if (ls.getLoginKey().equals(userkey)) {

			LoginSession dld = ls;
			loginSessionRepo.delete(dld);

			return "Logout succesfully!";

		} else {
			throw new LoginException("Please enter the valid login key!");
		}
	}
}
