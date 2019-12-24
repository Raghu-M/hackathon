package com.bank.retailbanking.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.CustomerRequestDto;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.dto.LoginResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.EmailAlreadyExistException;
import com.bank.retailbanking.exception.UserNameAlreadyExistException;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.util.LoginUtil;

/**
 * This service is having all the implementations of methods of the customers.
 * 
 * @author PriyaDharshini S
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * This will inject all the methods in the customerRepository.
	 */
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * This method is used authenticate the customer.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 */
	@Override
	public LoginResponseDto authenticateCustomer(LoginRequestDto loginRequestDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		Customer customer = customerRepository.findByUserName(loginRequestDto.getUserName());
		if (customer == null) {
			loginResponseDto = LoginUtil.loginFailure();
		} else if (customer.getPassword().equals(loginRequestDto.getPassword())) {
			loginResponseDto.setCustomerId(customer.getCustomerId());
			loginResponseDto.setMessage(Constant.LOGIN_SUCCESS);
			loginResponseDto.setStatusCode(Constant.ACCEPTED);
		} else {
			loginResponseDto = LoginUtil.loginFailure();
		}
		return loginResponseDto;
	}

	/**
	 * this method is used to add the customer.  
	 * 
	 * @param customerRequestDto which includes firstName,lastName,dateOfbirth,
	 *                           emailId,address,mobileNo,userName,password
	 * @return customerResponseDto which includes statusCode and message
	 * @throws UserNameAlreadyExistException 
	 * @throws EmailAlreadyExistException 
	 */

	@Override
	public CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto) throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		Customer customer = new Customer();
		Account account = new Account();
		Integer age = LocalDate.now().getYear() - customerRequestDto.getDateOfBirth().getYear();
		if (age < 18) {
			logger.info("registration failed due to age restriction");
			customerResponseDto.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			customerResponseDto.setMessage(Constant.LESSER_AGE);
			return customerResponseDto;
		}
		if (customerRepository.findByUserName(customerRequestDto.getUserName()) != null) {
			
			logger.info("registration failed, userName already exist");
			throw new UserNameAlreadyExistException("registration failed as userName already exist");
			
		}
		if (customerRepository.findByEmailId(customerRequestDto.getEmailId()) != null) {
			logger.info("registration failed as E-mail already exist");
			throw new EmailAlreadyExistException("registration failed as userName already exist");
		}
		BeanUtils.copyProperties(customerRequestDto, customer);
		customerRepository.save(customer);
		logger.info("customer registered");

		account.setBalance(Constant.MINIMUM_BALANCE);
		account.setAccountType(Constant.ACCOUNT_TYPE_SAVINGS);
		account.setAccountStatus(Constant.ACCOUNT_STATUS_ACTIVE);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.info("account created");

		customerResponseDto.setStatusCode(HttpStatus.OK.value());
		customerResponseDto.setMessage(Constant.SUCCESS);
		logger.info("registration successsful");

		return customerResponseDto;
	}

}