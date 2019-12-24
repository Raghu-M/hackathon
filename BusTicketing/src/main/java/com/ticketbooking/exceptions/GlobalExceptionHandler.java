package com.ticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ticketbooking.constants.Constant;
import com.ticketbooking.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<ErrorDto> ticketNotFoundException(){
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.TICKET_NOT_FOUND);
		errorDto.setStatusCode(Constant.TICKET_CANCEL_FAILED);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

}
