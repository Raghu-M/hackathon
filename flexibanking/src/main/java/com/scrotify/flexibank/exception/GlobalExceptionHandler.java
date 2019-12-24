package com.scrotify.flexibank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNameAlreadyExistException.class)
	public ResponseEntity<ErrorDto> userNameAlreadyExistException(UserNameAlreadyExistException e) {

		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
	}

	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorDto> emailAlreadyExistException() {

		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.EMAIL_ALREADY_EXISTS);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> userNotFoundException() {

		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.USER_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorDto> accountNotFoundException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.ACCOUNT_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}
	
	@ExceptionHandler(InvalidCreditCardDetailsException.class)
	public ResponseEntity<ErrorDto> invalidCreditCardDetailsException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.CREDIT_CARD_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}
	
	@ExceptionHandler(CreditCardLimitException.class)
	public ResponseEntity<ErrorDto> creditCardLimitException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.CREDIT_CARD_LIMIT);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}


}
