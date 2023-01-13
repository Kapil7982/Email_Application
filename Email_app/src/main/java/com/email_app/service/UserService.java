package com.email_app.service;

import java.util.List;
import com.email_app.exception.EmailException;
import com.email_app.exception.LoginException;
import com.email_app.exception.UserException;
import com.email_app.model.Email;
import com.email_app.model.User;
import com.email_app.model.UserDTO;
import com.email_app.model.UserUpdateDTO;

public interface UserService {

	public User register(UserDTO userDTO) throws UserException;

	public List<Email> checkAllEmails(String userEmail) throws UserException, EmailException, LoginException;

	public List<Email> checkAllStarredEmails(String userEmail) throws UserException, EmailException, LoginException;

	public User updateUser(UserUpdateDTO userUpdateDTO, String userEmail) throws UserException, LoginException;
}
