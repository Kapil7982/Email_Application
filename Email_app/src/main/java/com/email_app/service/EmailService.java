package com.email_app.service;

import com.email_app.exception.EmailException;
import com.email_app.exception.LoginException;
import com.email_app.exception.UserException;
import com.email_app.model.Email;
import com.email_app.model.EmailDTO;

public interface EmailService {

	public Email sendEmail(EmailDTO emailDto, String fromEmail, String toEmail)
			throws EmailException, UserException, LoginException;

	public Email starredEmail(Integer emailId) throws EmailException, LoginException;

	public Email deleteEmail(Integer emailId) throws EmailException, LoginException, UserException;

}
