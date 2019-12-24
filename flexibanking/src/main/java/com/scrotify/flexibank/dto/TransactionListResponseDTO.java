package com.scrotify.flexibank.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionListResponseDTO {
	private String transactionType;
	private Double transactionAmount;
	private String transactionDescription;
	private Integer transactionId;
	private LocalDate transactionDate;
}