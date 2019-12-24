package com.bank.retailbanking.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TransactionDto {
	
	private Integer transactionId;
	private String transactionType;
	private Double transactionAmount;
	private String transactionDescription;
	private LocalDate transactionDate;

}