package com.scrotify.flexibank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexibank.dto.FundTransferRequestDto;
import com.scrotify.flexibank.dto.TransactionRequestDto;
import com.scrotify.flexibank.entity.Transaction;
import com.scrotify.flexibank.exception.CreditCardLimitException;
import com.scrotify.flexibank.exception.InvalidCreditCardDetailsException;
import com.scrotify.flexibank.service.TransactionService;


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
	 * @param FundTransferRequestDto which includes fromAccoutNumber, 
	 *                               toAccountNumber, amount, description.
	 * 
	 * @return This has the return type of fundTransferResponseDto.This returns
	 *         statusMessage and statusCode.
	 * @throws InvalidCreditCardDetailsException 
	 * @throws CreditCardLimitException 
	 */
	@PostMapping("fund-transfer")
	public ResponseEntity<Integer> fundTransfer(@RequestBody FundTransferRequestDto fundTransferRequestDto) throws InvalidCreditCardDetailsException, CreditCardLimitException {
		logger.info("transfering fund..");
		return ResponseEntity.ok().body(transactionService.fundTransfer(fundTransferRequestDto).getStatusCode());
	}

	/**
	 * This API is used to get the transaction list
	 * 
	 * @pathVariable AccountNumber and month and year.
	 * @return This has the return type of transactionResponseDto.This returns
	 *         last transactions and statusCode.
	 */
	@PostMapping
	public ResponseEntity<List<Transaction>> getTransactions(@RequestBody TransactionRequestDto transactionRequestDto) {
		logger.info("to transactions");
		return ResponseEntity.ok().body(transactionService.getTransactions(transactionRequestDto));
	}

}
