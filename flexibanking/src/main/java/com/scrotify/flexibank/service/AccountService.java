package com.scrotify.flexibank.service;

import java.util.List;

import com.scrotify.flexibank.entity.Account;

/**
 * This service is having all the methods of the account.
 */
public interface AccountService {


	List<Account> getAccounts(Integer customerId);

}