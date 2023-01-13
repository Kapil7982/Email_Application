package com.email_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.email_app.model.LoginSession;

@Repository
public interface LoginSessionRepo extends JpaRepository<LoginSession, String> {

	public LoginSession findByEmail(String email);

}
