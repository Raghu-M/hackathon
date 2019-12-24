package com.cassini.mortgage.service;

import java.util.List;

import com.cassini.mortgage.entity.Account;

public interface AccountService {
	
	List<Account> getAccounts(Long accountNumber);

}
