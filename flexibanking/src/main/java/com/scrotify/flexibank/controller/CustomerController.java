package com.scrotify.flexibank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexibank.dto.CustomerRequestDto;
import com.scrotify.flexibank.dto.CustomerResponseDto;
import com.scrotify.flexibank.dto.LoginRequestDto;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.exception.EmailAlreadyExistException;
import com.scrotify.flexibank.exception.UserNameAlreadyExistException;
import com.scrotify.flexibank.exception.UserNotFoundException;
import com.scrotify.flexibank.service.CustomerService;


/**
 * This Controller is having the customer related functionalities. This
 * Controller will call the CustomerService in which the implementations are
 * done.
 * 
 * @API It has a method authenticateCustomer which will authenticate the user.
 */
@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

	/**
	 * This will inject all the implementations in the CustomerService.
	 */
	@Autowired
	CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	/**
	 * This API has a method authenticateCustomer which will authenticate the user.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException occurs when user not found
	 */
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		logger.info("Entering into authenticateCustomer in controller");
		return ResponseEntity.ok().body(customerService.authenticateCustomer(loginRequestDto));
	}

	/**
	 * In this method the customer fills the registration form and opens an account
	 * 
	 * @param customerRequestDto which includes firstName,lastName,dateOfbirth,
	 *                           emailId,address,mobileNo,userName,password
	 * @return customerResponseDto which includes statusCode and message
	 * 
	 * @throws UserNameAlreadyExistException when username already exists
	 * @throws EmailAlreadyExistException when emailId already exists
	 */

	@PostMapping("")
	public CustomerResponseDto registerCustomer(@RequestBody CustomerRequestDto customerRequestDto)
			throws UserNameAlreadyExistException, EmailAlreadyExistException {
		logger.info("Registering customer....");
		return customerService.registerCustomer(customerRequestDto);
	}

}