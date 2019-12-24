package com.bank.retailbanking.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.dto.TransferAccountDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.service.AccountService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountControllerTest {
	
	@InjectMocks
	AccountController accountController;
	
	@Mock
	AccountService accountService;
	
	
	AccountSummaryResponseDto accountSummaryResponseDto = null;
	@Before
	public void setUp()
	{
		accountSummaryResponseDto = new AccountSummaryResponseDto();
		accountSummaryResponseDto.setMessage(Constant.SUCCESS);
		accountSummaryResponseDto.setStatusCode(Constant.ACCEPTED);
		
		Customer customer = new Customer();
		customer.setCustomerId(1);
	}
	
	@Test
	public void testFindAllAccountNumbersPositive() {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setUserName("test");
		account.setAccountNumber(1111111L);
		Account account1 = new Account();
		account1.setAccountNumber(111112L);
		Customer customer1 = new Customer();
		customer1.setUserName("test");
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(account1);
		TransferAccountDto transferAccountDto = new TransferAccountDto();
		transferAccountDto.setAccountNumber(account.getAccountNumber());
		transferAccountDto.setUserName(customer.getUserName());
		List<TransferAccountDto> TransferAccountDtos = new ArrayList<>();
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setTransferAccounts(TransferAccountDtos);
		accountResponseDto.setMessage(Constant.SUCCESS);
		accountResponseDto.setStatusCode(Constant.ACCEPTED);
		Mockito.when(accountService.getAllAccountNumber(account.getAccountNumber())).thenReturn(accountResponseDto);
		AccountResponseDto accountResponseDto1 = accountController.findAllAccountNumbers(account.getAccountNumber());
		assertNotNull(accountResponseDto1);
	}

	/**
	* This method is used to orderProductsTest.
	* @param orderRequestDto This is the parameters to add products method
	* @return This returns orderResponseDto.
	*/
	@Test
	public void testAccountSummaryTest()
	{
		
		Mockito.when(accountService.accountSummary(1)).thenReturn(accountSummaryResponseDto);
		AccountSummaryResponseDto response = accountController.accountSummary(1);
		Integer expected = Constant.ACCEPTED;
		assertEquals(expected, response.getStatusCode());
	}
	
	

}
