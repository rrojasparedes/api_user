package com.sermaluc.springboot.app.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sermaluc.springboot.app.dto.CustomErrorResponseDTO;

import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.core.env.Environment;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private Environment env;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		CustomErrorResponseDTO errorResponse = new CustomErrorResponseDTO(env.getProperty("error.jsonRequest"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException ex) {
		CustomErrorResponseDTO errorResponse = new CustomErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException ex) {
		CustomErrorResponseDTO errorResponse = new CustomErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<?> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex) {
		CustomErrorResponseDTO errorResponse = new CustomErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
