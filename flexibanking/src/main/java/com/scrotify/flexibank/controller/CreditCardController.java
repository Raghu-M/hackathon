package com.scrotify.flexibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.exception.AccountNotFoundException;
import com.scrotify.flexibank.exception.CreditCardNotFoundException;
import com.scrotify.flexibank.service.CreditCardService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is having the credit card related functionalities. This
 * Controller will call the creditCardService in which the implementations are
 * done.
 * 
 * @API It has a method getCreditCard which will find the credit card details of
 *      customer.
 */
@RestController
@Slf4j
@RequestMapping("/customers/{customerId}/accounts/{accountNumber}/credit-card")
@CrossOrigin
public class CreditCardController {

	/**
	 * This will inject all the implementations in the creditCardService.
	 */
	@Autowired
	CreditCardService creditCardService;

	/**
	 * This API is used to get credit card details of a customer.
	 * 
	 * @pathVariable accountNumber.This is the accountNumber of the current customer.
	 * @return This returns the credit card details of the current customer
	 * @throws AccountNotFoundException 
	 * @throws CreditCardNotFoundException 
	 */
	@GetMapping
	public ResponseEntity<CreditCard> getCreditCard(@PathVariable Long accountNumber) throws AccountNotFoundException, CreditCardNotFoundException {
		log.info("getCreditCard controller : getting credit card detail");
		return ResponseEntity.ok().body(creditCardService.getCreditCard(accountNumber));
	}

}
