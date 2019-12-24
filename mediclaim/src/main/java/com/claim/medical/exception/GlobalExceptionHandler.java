package com.claim.medical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.claim.medical.constants.Constant;
import com.claim.medical.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> UserNotFoundException(UserNotFoundException exception) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.USER_NOT_FOUND);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidClaimAmountException.class)
	public ResponseEntity<ErrorDto> invalidClaimAmountException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.CLAIM_AMOUNT_INVALID);
		errorDto.setStatusCode(Constant.INVALID_CLAIM);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);
	}

	@ExceptionHandler(PolicyExpiredException.class)
	public ResponseEntity<ErrorDto> policyExpiredException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.POLICY_EXPIRED);
		errorDto.setStatusCode(Constant.POLICY_EXPIRED_STATUS);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);
	}

	@ExceptionHandler(PolicyHolderNotFoundException.class)
	public ResponseEntity<ErrorDto> policyHolderNotFoundException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.POLICY_HOLDER_NOT_FOUND);
		errorDto.setStatusCode(Constant.POLICY_HOLDER_NOT_FOUND_STATUS);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ErrorDto> policyNotFoundException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.POLICY_NOT_FOUND);
		errorDto.setStatusCode(Constant.POLICY_NOT_FOUND_STATUS);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

	@ExceptionHandler(AlreadyClaimedException.class)
	public ResponseEntity<ErrorDto> alreadyClaimedException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.ALREADY_CLAIMED);
		errorDto.setStatusCode(Constant.ALREADY_CLAIMED_STATUS);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);
	}

}
