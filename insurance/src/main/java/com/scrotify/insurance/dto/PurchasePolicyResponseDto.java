package com.scrotify.insurance.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PurchasePolicyResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private Integer statusCode;

}
