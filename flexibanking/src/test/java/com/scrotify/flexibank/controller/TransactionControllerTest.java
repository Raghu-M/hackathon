package com.scrotify.flexibank.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.FundTransferRequestDto;
import com.scrotify.flexibank.dto.FundTransferResponseDto;
import com.scrotify.flexibank.dto.TransactionRequestDto;
import com.scrotify.flexibank.entity.Transaction;
import com.scrotify.flexibank.exception.CreditCardLimitException;
import com.scrotify.flexibank.exception.InvalidCreditCardDetailsException;
import com.scrotify.flexibank.service.TransactionService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionControllerTest {

	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionService transactionService;

	@Test
	public void testFundTransferSuccess() throws InvalidCreditCardDetailsException, CreditCardLimitException {

		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setTransactionAmount(3000.0);

		FundTransferResponseDto expected = new FundTransferResponseDto();
		expected.setStatusCode(Constant.ACCEPTED);
		Mockito.when(transactionService.fundTransfer(fundTransferRequestDto)).thenReturn(expected);
		Integer actual = transactionController.fundTransfer(fundTransferRequestDto).getBody();
		assertEquals(expected.getStatusCode(), actual);

	}

	/**
	 * This method is used to get orders by orderId.
	 * 
	 * @param orderId This is the parameters to get ordered products method
	 * @return This returns ordereResponseDto.
	 */

	@Test
	public void monthlyTransactionsTest() throws Exception {

		Mockito.when(transactionService.getTransactions(Mockito.any())).thenReturn(new ArrayList<>());
		ResponseEntity<List<Transaction>> orders = transactionController.getTransactions(new TransactionRequestDto());
		Assert.assertNotNull(orders);
	}

}
