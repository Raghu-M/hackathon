package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;

public interface TransactionService {

	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto);
	public TransactionResponseDto monthlyTransactions(TransactionRequestDto transactionRequestDto);

}
