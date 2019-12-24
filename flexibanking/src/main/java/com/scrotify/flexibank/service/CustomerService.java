package com.scrotify.flexibank.service;

import com.scrotify.flexibank.dto.CustomerRequestDto;
import com.scrotify.flexibank.dto.CustomerResponseDto;
import com.scrotify.flexibank.dto.LoginRequestDto;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.exception.EmailAlreadyExistException;
import com.scrotify.flexibank.exception.UserNameAlreadyExistException;
import com.scrotify.flexibank.exception.UserNotFoundException;

/**
 * This service is having all the methods of the customer.
 * 
 * @author Raghu M
 */
public interface CustomerService {

	/**
	 * This method is used authenticate the customer.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException
	 */
	Customer authenticateCustomer(LoginRequestDto loginRequestDto) throws UserNotFoundException;

	CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto)
			throws UserNameAlreadyExistException, EmailAlreadyExistException;

}