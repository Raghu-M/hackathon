package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequestDto {
	private Long accountNumber;
	private Integer month;
	private Integer year;
}
