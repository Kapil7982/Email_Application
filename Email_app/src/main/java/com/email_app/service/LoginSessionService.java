package com.email_app.service;

import com.email_app.exception.LoginException;
import com.email_app.model.LoginSession;

public interface LoginSessionService {

	public String login(LoginSession loginSession) throws LoginException;

	public String logout(String email, String userkey) throws LoginException;

}
