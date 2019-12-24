package com.scrotify.flexibank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.FundTransferRequestDto;
import com.scrotify.flexibank.dto.TransactionRequestDto;
import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.entity.Transaction;
import com.scrotify.flexibank.exception.CreditCardLimitException;
import com.scrotify.flexibank.exception.InvalidCreditCardDetailsException;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CreditCardRepository;
import com.scrotify.flexibank.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionServiceImpl transactionService;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountRepository accountRepository;
	
	@Mock
	CreditCardRepository creditCardRepository;
	
	Customer customer = new Customer();
	CreditCard creditCard = new CreditCard();
	Account account = new Account();
	FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
	
	@Before
	public void setup() {
		customer.setCustomerId(1);
		
		account.setAccountNumber(1L);
		account.setBalance(1.0);
		account.setCustomer(customer);
		
		
		creditCard.setAccount(account);
		creditCard.setCardLimit(100.0);
		creditCard.setCreditCardNumber(1L);
		creditCard.setCvv(1);
		creditCard.setExpiryDate(LocalDate.parse("2019-11-20"));
		creditCard.setPin(1);
		
		fundTransferRequestDto.setCreditCardNumber(1L);
		fundTransferRequestDto.setCvv(1);
		fundTransferRequestDto.setExpiryDate(LocalDate.parse("2019-11-11"));
		fundTransferRequestDto.setPin(1);
		fundTransferRequestDto.setToAccount(1L);
		fundTransferRequestDto.setTransactionAmount(1.0);
		fundTransferRequestDto.setTransactionDescription("test");
		
	}
	
	@Test(expected = InvalidCreditCardDetailsException.class)
	public void testFundTransferInvalidCreditCard() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		transactionService.fundTransfer(fundTransferRequestDto);
	}
	
	@Test(expected = InvalidCreditCardDetailsException.class)
	public void testFundTransferInvalidCreditCardCvv() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		creditCard.setCvv(2);
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		transactionService.fundTransfer(fundTransferRequestDto);
	}
	
	@Test(expected = InvalidCreditCardDetailsException.class)
	public void testFundTransferInvalidCreditCardPin() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		creditCard.setPin(2);
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		transactionService.fundTransfer(fundTransferRequestDto);
	
	}
	
	@Test(expected = InvalidCreditCardDetailsException.class)
	public void testFundTransferInvalidCreditCardExpiryDate() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
		creditCard.setExpiryDate(LocalDate.parse("2019-11-07"));
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		transactionService.fundTransfer(fundTransferRequestDto);
		
	}
	
	@Test
	public void testFundTransferAccountNotFound() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		String expected = Constant.NO_PAYEE_ACCOUNT;
		String actual = transactionService.fundTransfer(fundTransferRequestDto).getMessage();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransferInvalidTransactionAmount() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		fundTransferRequestDto.setTransactionAmount(0.0);
		String expected = Constant.TRANSACTION_FAILURE;
		String actual = transactionService.fundTransfer(fundTransferRequestDto).getMessage();
		assertEquals(expected, actual);
		fundTransferRequestDto.setTransactionAmount(1.0);
	}
	
	@Test
	public void testFundTransferSuccess() throws InvalidCreditCardDetailsException, CreditCardLimitException {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));
		Mockito.when(creditCardRepository.findById(1L)).thenReturn(Optional.ofNullable(creditCard));
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(new Account());
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(new Transaction());
		
		String expected = Constant.TRANSACTION_SUCCESS;
		String actual = transactionService.fundTransfer(fundTransferRequestDto).getMessage();
		assertEquals(expected, actual);
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

		Mockito.when(transactionRepository.getAllByAccountAndTransactionDateBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(transactions);
		List<Transaction> actual = transactionService.getTransactions(transactionRequestDto);
		assertNotNull(actual);

	}

	@Test
	public void getTransactionsTest() {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		Mockito.when(transactionRepository.findTop5ByAccountOrderByTransactionIdDesc(Mockito.any()))
				.thenReturn(new ArrayList<Transaction>());
		assertNotNull(transactionService.getTransactions(transactionRequestDto));
	}

}
