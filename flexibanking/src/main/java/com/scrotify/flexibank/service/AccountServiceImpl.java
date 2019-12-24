package com.scrotify.flexibank.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CustomerRepository;
import com.scrotify.flexibank.repository.TransactionRepository;


/**
 * This service is having all the implementations of methods of the accounts.
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;


	/**Z
	 * This method is used to get accountSummary which will find all the
	 * accountNumbers
	 * 
	 * @param customerId.This is the customerId of the customer.
	 * @return This has the return type of list of account.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */
	@Override
	public List<Account> getAccounts(Integer customerId) {
		logger.info("to get account summary");
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		return accountRepository.findByCustomer(customer);
	}

}