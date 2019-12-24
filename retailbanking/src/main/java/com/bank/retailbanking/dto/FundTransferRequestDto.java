package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FundTransferRequestDto {
	private Double transactionAmount;
	private String transactionDescription;
	private Long fromAccount;
	private Long toAccount;

}
