package com.bank.retailbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.service.TransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	/**
	 * This API is used to transfer the fund.
	 * 
	 * @param FundTransferRequestDto which includes payee accountNumber, payer
	 *                               accountNumber, amount, description.
	 * 
	 * @return This has the return type of fundTransferResponseDto.This returns
	 *         statusMessage and statusCode.
	 */
	@PostMapping("fund-transfer")
	public FundTransferResponseDto fundTransfer(@RequestBody FundTransferRequestDto fundTransferRequestDto) {
		logger.info("transfering fund..");
		return transactionService.fundTransfer(fundTransferRequestDto);
	}

	/**
	 * This API is used to find monthlyTransaction which will find monthly
	 * transaction
	 * 
	 * @pathVariable AccountNumber and month and year.
	 * @return This has the return type of transactionResponseDto.This returns
	 *         monthly transactions and statusCode.
	 */
	@PostMapping("/monthly")
	public TransactionResponseDto monthlyTransactions(@RequestBody TransactionRequestDto transactionRequestDto) {
		logger.info("to get Monthly transactions");
		return transactionService.monthlyTransactions(transactionRequestDto);
	}

}
