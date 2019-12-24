package com.scrotify.flexibank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.service.AccountService;

/**
 * This Controller is having the account related functionalities. This
 * Controller will call the accountService in which the implementations are
 * done.
 * 
 * @API It has a method findAllAccountNumbers which will find all the
 *      accountNumbers except the current customer's accountNumber.
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
	 * This API is used to find accountSummary which will give list of accounts
	 * 
	 * @pathVariable customerId.This is the customerId of the current customer.
	 * @return This returns the account summary of the current customer
	 */

	@GetMapping(value = "/accountsummary/{customerId}")
	public ResponseEntity<List<Account>> getAccounts(@PathVariable Integer customerId) {
		logger.info("fetching Account Summary..");
		return ResponseEntity.ok().body(accountService.getAccounts(customerId));

	}

}