package com.bank.retailbanking.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.TransactionRepository;
import com.bank.retailbanking.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionServiceImpl transactionService;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountRepository accountRepository;

	@Test
	public void testFundTransferAccountNotFound() {

		FundTransferResponseDto actual = new FundTransferResponseDto();

		FundTransferResponseDto expected = new FundTransferResponseDto();
		expected.setMessage(Constant.NO_PAYER_ACCOUNT);
		expected.setStatusCode(Constant.NOT_ACCEPTABLE);

		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccount(2L);
		fundTransferRequestDto.setToAccount(5L);
		fundTransferRequestDto.setTransactionAmount(300.0);
		fundTransferRequestDto.setTransactionDescription("testing");

		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		Mockito.when(accountRepository.findById(7L)).thenReturn(Optional.of(new Account()));
		actual = transactionService.fundTransfer(fundTransferRequestDto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());

		fundTransferRequestDto.setFromAccount(1L);
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(new Account()));
		actual = transactionService.fundTransfer(fundTransferRequestDto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());

	}

	@Test
	public void testFundTransferSuccess() {

		FundTransferResponseDto actual = new FundTransferResponseDto();

		FundTransferResponseDto expected = new FundTransferResponseDto();
		expected.setMessage(Constant.TRANSACTION_SUCCESS);
		expected.setStatusCode(Constant.ACCEPTED);

		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccount(1L);
		fundTransferRequestDto.setToAccount(2L);
		fundTransferRequestDto.setTransactionAmount(300.0);
		fundTransferRequestDto.setTransactionDescription("testing");
		Account account = new Account();
		account.setBalance(900.0);

		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
		Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(account));
		Mockito.when(accountRepository.save(new Account())).thenReturn(new Account());
		Mockito.when(transactionRepository.save(new Transaction())).thenReturn(new Transaction());
		actual = transactionService.fundTransfer(fundTransferRequestDto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());

	}

	@Test
	public void testFundTransferFailure() {

		FundTransferResponseDto actual = new FundTransferResponseDto();

		FundTransferResponseDto expected = new FundTransferResponseDto();
		expected.setMessage(Constant.TRANSACTION_FAILURE);
		expected.setStatusCode(Constant.NOT_ACCEPTABLE);

		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccount(1L);
		fundTransferRequestDto.setToAccount(2L);
		fundTransferRequestDto.setTransactionAmount(-45.0);
		fundTransferRequestDto.setTransactionDescription("testing");
		Account account = new Account();
		account.setBalance(200.0);
		
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
		Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(account));
		actual = transactionService.fundTransfer(fundTransferRequestDto);
		assertEquals(Constant.INVALID_AMOUNT, actual.getStatusCode());
		
		fundTransferRequestDto.setTransactionAmount(500.0);
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
		Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(account));
		Mockito.when(accountRepository.save(new Account())).thenReturn(new Account());
		Mockito.when(transactionRepository.save(new Transaction())).thenReturn(new Transaction());
		actual = transactionService.fundTransfer(fundTransferRequestDto);
		System.out.println(actual.getMessage());
		assertEquals(expected.getStatusCode(), actual.getStatusCode());

	}

	@Test
	public void testMonthlyTransactions() {

		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(1L);
		transactionRequestDto.setMonth(01);
		transactionRequestDto.setYear(2019);
		Account account = new Account();
		account.setAccountNumber(1L);
		List<Transaction> transactions = new ArrayList<>();
		
		Transaction transaction = new Transaction();
		transaction.setTransactionType("debit");
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionDescription("testing");
		transaction.setTransactionAmount(300.0);
		transaction.setAccount(account);
		transactions.add(transaction);
		
		Mockito.when(transactionRepository.getAllByAccountAndTransactionDateBetween(Mockito.any() , Mockito.any(),Mockito.any())).thenReturn(transactions);
		TransactionResponseDto actual = transactionService.monthlyTransactions(transactionRequestDto);
		TransactionResponseDto expected = new TransactionResponseDto();
		expected.setStatuscode(Constant.ACCEPTED);
		assertEquals(expected.getStatuscode(), actual.getStatuscode());
	
		
	}

}
