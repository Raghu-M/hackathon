package com.scrotify.flexibank.service;

import java.util.List;

import com.scrotify.flexibank.dto.FundTransferRequestDto;
import com.scrotify.flexibank.dto.FundTransferResponseDto;
import com.scrotify.flexibank.dto.TransactionRequestDto;
import com.scrotify.flexibank.entity.Transaction;
import com.scrotify.flexibank.exception.CreditCardLimitException;
import com.scrotify.flexibank.exception.InvalidCreditCardDetailsException;


public interface TransactionService {

	FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto) throws InvalidCreditCardDetailsException, CreditCardLimitException;
	
	List<Transaction> getTransactions(TransactionRequestDto transactionRequestDto);
	

}
