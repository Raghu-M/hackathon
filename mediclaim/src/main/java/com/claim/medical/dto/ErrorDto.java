package com.claim.medical.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

	private Integer statusCode;
	private String message;

}
