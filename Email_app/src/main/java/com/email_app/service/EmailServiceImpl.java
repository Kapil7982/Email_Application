package com.email_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.email_app.exception.EmailException;
import com.email_app.exception.LoginException;
import com.email_app.exception.UserException;
import com.email_app.model.Email;
import com.email_app.model.EmailDTO;
import com.email_app.model.LoginSession;
import com.email_app.model.User;
import com.email_app.repo.EmailRepo;
import com.email_app.repo.LoginSessionRepo;
import com.email_app.repo.UserRepo;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRepo emailRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LoginSessionRepo loginSessionRepo;

	
	public void checkingLoginStatus(String email) throws LoginException {

		LoginSession ls = loginSessionRepo.findByEmail(email);
		
		if (ls == null)
			throw new LoginException("Please login first !");
	}


	public User getuser(String userEmail) throws UserException {

		User user = userRepo.findByEmail(userEmail);
		if (user == null)
			throw new UserException("User not found with email : " + userEmail);
		return user;
	}

	
	public Email getEmail(Integer emailId) throws EmailException {

		Optional<Email> emailOpt = emailRepo.findById(emailId);
		if (emailOpt.isEmpty())
			throw new EmailException("Couldn't found email with thid Id : " + emailId);

		return emailOpt.get();
	}

	@Override
	public Email sendEmail(EmailDTO emailDTO, String fromEmail, String toEmail)
			throws EmailException, UserException, LoginException {

		checkingLoginStatus(fromEmail);

		
		User user = getuser(fromEmail);

		if (emailDTO == null)
			throw new EmailException("Email Id can't be Empty!");

		Email email = new Email();
		email.setFromEmail(fromEmail);
		email.setToEmail(toEmail);
		email.setSubject(emailDTO.getSubject());
		email.setDescription(emailDTO.getDescription());
		email.setStar(emailDTO.getStar());

		user.getEmailList().add(email);
		return emailRepo.save(email);
	}

	@Override
	public Email starredEmail(Integer emailId) throws EmailException, LoginException {

		
		Email email = getEmail(emailId);

		if (email.getStar()) {

			email.setStar(false);
			return emailRepo.save(email);

		} else {
			email.setStar(true);
			return emailRepo.save(email);
		}

	}

	@Override
	public Email deleteEmail(Integer emailId) throws EmailException, LoginException, UserException {

	
		Email em = getEmail(emailId);

		String UsersEmail = em.getFromEmail();
		
		User user = getuser(UsersEmail);

		Email deleted_Emails = null;

		List<Email> emailList = user.getEmailList();
		
		for (int i = 0; i < emailList.size(); i++) {
			
			Email email = emailList.get(i);
			
			if (email.getId() == emailId) {
				
				deleted_Emails = email;
				
				emailList.remove(i);

			}
		}
		user.setEmailList(emailList);
		userRepo.save(user);
		return deleted_Emails;
	}

}
