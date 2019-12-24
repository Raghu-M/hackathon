package com.claim.medical.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ResponseError implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String message;
	private int statusCode;
}
