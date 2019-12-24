package com.matrimony.cassini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNameAlreadyExistException.class)
	public ResponseEntity<ErrorDto> userNameAlreadyExistException(String message) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.USER_NAME_ALREADY_EXIST);
		errorDto.setStatusCode(HttpStatus.CONFLICT.value());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
	}



}
