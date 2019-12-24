package com.scrotify.flexibank.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.repository.AccountRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Mock
	AccountRepository accountRepository;


	@Test
	public void testGetAccounts() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		Mockito.when(accountRepository.findByCustomer(customer)).thenReturn(new ArrayList<>());
		assertNotNull(accountServiceImpl.getAccounts(1));
	}



}
