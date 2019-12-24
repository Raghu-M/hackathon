package com.bank.retailbanking.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.controller.TransactionController;
import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.service.TransactionService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionControllerTest {
	
	@InjectMocks
	TransactionController transactionController;
	
	@Mock
	TransactionService transactionService;
	
	
	TransactionResponseDto transactionResponseDto = null;
	TransactionRequestDto transactionRequestDto = null;

	@Before
	public void setUp() {
		transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setMessage(Constant.SUCCESS);
		transactionResponseDto.setStatuscode(Constant.ACCEPTED);

		transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(60987651L);
		transactionRequestDto.setMonth(11);
		transactionRequestDto.setYear(2019);
	}
	
	@Test
	public void testFundTransferSuccess() {
		
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setTransactionAmount(3000.0);
		
		FundTransferResponseDto expected = new FundTransferResponseDto();
		expected.setStatusCode(Constant.ACCEPTED);
		Mockito.when(transactionService.fundTransfer(fundTransferRequestDto)).thenReturn(expected);
		FundTransferResponseDto actual = transactionController.fundTransfer(fundTransferRequestDto);
		assertEquals(expected, actual);
		
		
	}


	/**
	* This method is used to get orders by orderId.
	* @param orderId This is the parameters to get ordered products method
	* @return This returns ordereResponseDto.
	*/
	
	@Test
	public void monthlyTransactionsTest() throws Exception {
		transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(60987651L);
		transactionRequestDto.setMonth(11);
		transactionRequestDto.setYear(2019);
		transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setMessage(Constant.SUCCESS);
		transactionResponseDto.setStatuscode(Constant.ACCEPTED);

		Mockito.when(transactionService.monthlyTransactions(transactionRequestDto)).thenReturn(transactionResponseDto);
		TransactionResponseDto orders = transactionController.monthlyTransactions(transactionRequestDto);
		Assert.assertNotNull(orders);
	}

}
