package com.scrotify.flexibank.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.CustomerRequestDto;
import com.scrotify.flexibank.dto.CustomerResponseDto;
import com.scrotify.flexibank.dto.EmailDto;
import com.scrotify.flexibank.dto.LoginRequestDto;
import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.exception.EmailAlreadyExistException;
import com.scrotify.flexibank.exception.UserNameAlreadyExistException;
import com.scrotify.flexibank.exception.UserNotFoundException;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CreditCardRepository;
import com.scrotify.flexibank.repository.CustomerRepository;
import com.scrotify.flexibank.util.EmailService;

/**
 * This service is having all the implementations of methods of the customers.
 * 
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

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	CreditCardRepository creditCardRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * This method is used authenticate the customer.
	 * 
	 * loginRequestDto is object which includes userName and password
	 * 
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException when userName not found
	 */
	@Override
	public Customer authenticateCustomer(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		Optional<Customer> customer = customerRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}

	}

	/**
	 * This method is used to register the customer.
	 * 
	 * @param customerRequestDto which includes firstName,lastName,dateOfbirth,
	 *                           emailId,address,mobileNo,userName,password
	 * @return customerResponseDto which includes statusCode and message
	 * @throws UserNameAlreadyExistException
	 * @throws EmailAlreadyExistException
	 */
	@Override
	@Transactional
	public CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto)
			throws UserNameAlreadyExistException, EmailAlreadyExistException {

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
		if (customerRepository.findByUserName(customerRequestDto.getUserName()).isPresent()) {

			logger.info("registration failed, userName already exist");
			throw new UserNameAlreadyExistException("registration failed as userName already exist");

		}
		if (customerRepository.findByEmailId(customerRequestDto.getEmailId()).isPresent()) {
			logger.info("registration failed as E-mail already exist");
			throw new EmailAlreadyExistException("registration failed as userName already exist");
		}
		BeanUtils.copyProperties(customerRequestDto, customer);
		customerRepository.save(customer);
		logger.info("customer registered");

		account.setBalance(0.0);
		account.setAccountType(Constant.ACCOUNT_TYPE_CREDIT_CARD);
		account.setAccountStatus(Constant.ACCOUNT_STATUS_ACTIVE);
		account.setCreationDate(LocalDate.now());
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.info("account created");

		CreditCard creditCard = new CreditCard();
		creditCard.setAccount(account);
		long number = (long) (new Random().nextDouble()*100000000000000L);
		creditCard.setCreditCardNumber(Long.valueOf(52+String.format("%014d", number)));
		System.out.println(creditCard.getCreditCardNumber());
		creditCard.setPin(new Random().nextInt(8999) + 1000);
		creditCard.setCvv(new Random().nextInt(899) + 100);
		creditCard.setCardLimit(customerRequestDto.getSalary() * 2);
		creditCard.setExpiryDate(LocalDate.now().plusYears(5));
		creditCardRepository.save(creditCard);
		logger.info("credit card created");

		EmailDto emailDto = new EmailDto();
		BeanUtils.copyProperties(creditCard, emailDto);
		emailDto.setEmail(customerRequestDto.getEmailId());
		emailDto.setName(customerRequestDto.getName());
		new EmailService().sendEmail(emailDto);

		customerResponseDto.setMessage(Constant.SUCCESS);
		customerResponseDto.setStatusCode(HttpStatus.OK.value());
		logger.info("registration successsful");

		return customerResponseDto;
	}

}