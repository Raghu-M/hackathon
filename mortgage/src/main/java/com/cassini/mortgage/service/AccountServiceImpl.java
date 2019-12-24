package com.cassini.mortgage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.mortgage.entity.Account;
import com.cassini.mortgage.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<Account> getAccounts(Long accountNumber) {

		return accountRepository.findAllById(accountNumber);
	}

}
