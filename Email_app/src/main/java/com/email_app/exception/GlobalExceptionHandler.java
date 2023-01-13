package com.email_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException lg, WebRequest re) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(lg.getMessage());
		med.setDescription(re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailException.class)
	public ResponseEntity<MyErrorDetails> PostExceptionHandler(EmailException me, WebRequest re) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(me.getMessage());
		med.setDescription(re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException me, WebRequest re) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(me.getMessage());
		med.setDescription(re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<MyErrorDetails> myIllegalArgumentExceptionHandler(IllegalArgumentException iae,
			WebRequest req) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(iae.getMessage());
		med.setDescription(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me,
			WebRequest re) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());
		med.setDescription(re.getDescription(false));
		return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException e, WebRequest req) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(e.getMessage());
		med.setDescription(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception me, WebRequest re) {
		MyErrorDetails med = new MyErrorDetails();
		med.setTime(LocalDateTime.now());
		med.setMessage(me.getMessage());
		med.setDescription(re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}
}
