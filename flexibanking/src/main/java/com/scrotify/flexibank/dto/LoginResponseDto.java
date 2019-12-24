package com.scrotify.flexibank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
	private String message;
	private Integer statusCode;
	private Integer customerId;

}
