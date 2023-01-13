package com.email_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.email_app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	public User findByEmail(String email);

}
