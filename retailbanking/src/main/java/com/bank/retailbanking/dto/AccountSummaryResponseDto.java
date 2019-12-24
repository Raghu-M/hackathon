package com.bank.retailbanking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AccountSummaryResponseDto {
	
	private Long accountNumber;
	private Double balance;
	private String name;
	private List<TransactionDto> transactions;
	private String message;
	private Integer statusCode;

}