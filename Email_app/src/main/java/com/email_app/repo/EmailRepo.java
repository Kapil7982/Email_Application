package com.email_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.email_app.model.Email;

@Repository
public interface EmailRepo extends JpaRepository<Email, Integer> {

}
