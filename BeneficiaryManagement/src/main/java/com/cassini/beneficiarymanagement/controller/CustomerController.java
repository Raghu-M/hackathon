package com.cassini.beneficiarymanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.service.CustomerService;

/**
 * This Controller has a method has the implementations of the customer.
 * 
 * @Api authenticateCustomer which will authenticate the user.
 * 
 * @author Yoganand.
 */
@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	/**
	 * This will inject all the implementations in the customerService.
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * This API has a method authenticateCustomer which will authenticate the user.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		logger.info("authenticateCustomer method - logging in customer ");
		return ResponseEntity.ok().body(customerService.authenticateCustomer(loginRequestDto));
	}
}
