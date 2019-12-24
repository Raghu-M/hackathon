package com.scrotify.flexibank.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmailDto {
	
	private Long creditCardNumber;
	private Integer cvv;
	private Integer pin;
	private LocalDate expiryDate;
	private String email;
	private String name;

}
