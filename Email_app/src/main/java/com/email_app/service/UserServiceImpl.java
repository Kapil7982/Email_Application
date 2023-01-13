package com.email_app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.email_app.exception.EmailException;
import com.email_app.exception.LoginException;
import com.email_app.exception.UserException;
import com.email_app.model.Email;
import com.email_app.model.LoginSession;
import com.email_app.model.User;
import com.email_app.model.UserDTO;
import com.email_app.model.UserUpdateDTO;
import com.email_app.repo.LoginSessionRepo;
import com.email_app.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LoginSessionRepo loginSessionRepo;


	public void checkingLoginStatus(String email) throws LoginException {

		LoginSession ls = loginSessionRepo.findByEmail(email);
		
		if (ls == null)
			throw new LoginException("Please enter the login credential!");
	}


	public User getuser(String userEmail) throws UserException {

		User user = userRepo.findByEmail(userEmail);
		if (user == null)
			throw new UserException("User not found with this email Id : " + userEmail);
		return user;
	}

	@Override
	public User register(UserDTO userDTO) throws UserException {

		User user = userRepo.findByEmail(userDTO.getEmail());
		
		if (user != null)
			throw new UserException("User already exists with email : " + userDTO.getEmail());

		User user1 = new User();
		user1.setEmail(userDTO.getEmail());
		user1.setFirstName(userDTO.getFirstName());
		user1.setLastName(userDTO.getLastName());
		user1.setMobileNumber(userDTO.getMobileNumber());
		user1.setDateOfBirth(userDTO.getDateOfBirth());
		user1.setPassword(userDTO.getPassword());

		return userRepo.save(user1);
	}

	@Override
	public List<Email> checkAllEmails(String userEmail) throws UserException, EmailException, LoginException {

		
		checkingLoginStatus(userEmail);

		
		User user = getuser(userEmail);

		List<Email> list = user.getEmailList();
		if (list.isEmpty())
			throw new EmailException("Couldn't found any email with this Id :- " + userEmail);

		return list;
	}

	@Override
	public List<Email> checkAllStarredEmails(String userEmail) throws UserException, EmailException, LoginException {

		
		checkingLoginStatus(userEmail);

		
		User user = getuser(userEmail);

		List<Email> list = new ArrayList<>();
		for (Email email : user.getEmailList()) {
			if (email.getStar())
				list.add(email);
		}

		if (list.isEmpty())
			throw new EmailException("User doesn't have any starred email");

		return list;
	}

	@Override
	public User updateUser(UserUpdateDTO userUpdateDTO, String userEmail) throws UserException, LoginException {

	
		User user = getuser(userEmail);

		user.setEmail(userEmail);
		user.setFirstName(userUpdateDTO.getFirstName());
		user.setLastName(userUpdateDTO.getLastName());
		user.setMobileNumber(userUpdateDTO.getMobileNumber());
		user.setDateOfBirth(userUpdateDTO.getDateOfBirth());
		user.setPassword(userUpdateDTO.getPassword());

		return userRepo.save(user);
	}

}
