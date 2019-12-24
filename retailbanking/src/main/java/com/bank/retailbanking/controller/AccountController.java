package com.bank.retailbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.service.AccountService;

/**
 * This Controller is having the account related functionalities. This
 * Controller will call the accountService in which the implementations are
 * done.
 * 
 * @API It has a method findAllAccountNumbers which will find all the
 *      accountNumbers except the current customer's accountNumber.
 * @author PriyaDharshini S
 */
@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	/**
	 * This will inject all the implementations in the accountService.
	 */
	@Autowired
	AccountService accountService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	 * This API is used to findAllAccountNumbers which will find all the
	 * accountNumbers except the current customer's accountNumber
	 * 
	 * @pathVariable accountNumber.This is the accountNumber of the current
	 *               customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	@GetMapping("/{accountNumber}")
	public AccountResponseDto findAllAccountNumbers(@PathVariable Long accountNumber) {
		logger.info("Entering into findAllAccountNumbers method");
		return accountService.getAllAccountNumber(accountNumber);

	}

	/**
	 * This API is used to find accountSummary which will find last five
	 * transactions and account number and name
	 * 
	 * @pathVariable customerId.This is the customerId of the current customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         last five transactions and accountNumbers and String of result along
	 *         with the statusCode.
	 */

	@GetMapping(value = "/accountsummary/{customerId}")
	public AccountSummaryResponseDto accountSummary(@PathVariable Integer customerId) {
		logger.info("fetching Account Summary..");
		return accountService.accountSummary(customerId);

	}
}