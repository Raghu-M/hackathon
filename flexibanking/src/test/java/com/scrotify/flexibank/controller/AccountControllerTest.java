package com.scrotify.flexibank.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.service.AccountService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountControllerTest {

	@InjectMocks
	AccountController accountController;

	@Mock
	AccountService accountService;



	/**
	 * This method is used to orderProductsTest.
	 * 
	 * @param orderRequestDto This is the parameters to add products method
	 * @return This returns orderResponseDto.
	 */
	@Test
	public void testAccountSummaryTest() {

		Mockito.when(accountService.getAccounts(1)).thenReturn(new ArrayList<>());
		ResponseEntity<List<Account>> response = accountController.getAccounts(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}



}
