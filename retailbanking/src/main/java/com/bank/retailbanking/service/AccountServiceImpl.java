package com.bank.retailbanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.dto.TransactionDto;
import com.bank.retailbanking.dto.TransferAccountDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.TransactionRepository;

/**
 * This service is having all the implementations of methods of the accounts.
 * 
 * @author PriyaDharshini S
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * This API is used to findAllAccountNumbers which will find all the
	 * accountNumbers except the current customer's accountNumber
	 * 
	 * @pathVariable accountNumber.This is the accountNumber of the current
	 *               customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	@Override
	public AccountResponseDto getAllAccountNumber(Long accountNumber) {
		List<Account> accounts = accountRepository.findAllByAccountNumberNot(accountNumber);
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		List<TransferAccountDto> transferAccountDtos = new ArrayList<>();
		if (accounts.isEmpty()) {
			accountResponseDto.setMessage(Constant.ACCOUNT_NOT_FOUND);
			accountResponseDto.setStatusCode(Constant.ACCOUNT_NOT_FOUND_CODE);
			accountResponseDto.setTransferAccounts(null);
		}
		else {
			for (Account account : accounts) {
				TransferAccountDto transferAccountDto = new TransferAccountDto();
				Customer customer = customerRepository.findById(account.getCustomer().getCustomerId()).orElse(null);
				transferAccountDto.setAccountNumber(account.getAccountNumber());
				transferAccountDto.setUserName(customer.getUserName());
				transferAccountDtos.add(transferAccountDto);
			}
			accountResponseDto.setTransferAccounts(transferAccountDtos);
			accountResponseDto.setMessage(Constant.SUCCESS);
			accountResponseDto.setStatusCode(Constant.ACCEPTED);
		}
		return accountResponseDto;
	}

	/**
	 * This API is used to get accountSummary which will find all the accountNumbers
	 * and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */

	@Override
	public AccountSummaryResponseDto accountSummary(Integer customerId) {
		logger.info("to get account summary");
		AccountSummaryResponseDto accountSummaryResponseDto = null;
		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<Account> accountDetails = accountRepository.findByCustomer(customer);
		List<Transaction> transactions = transactionRepository
				.findTop5ByAccountOrderByTransactionIdDesc(accountDetails);
		List<TransactionDto> transactionsList = new ArrayList<>();

		if (accountDetails.isPresent() && customer.isPresent()) {
			accountSummaryResponseDto = new AccountSummaryResponseDto();
			accountSummaryResponseDto.setAccountNumber(accountDetails.get().getAccountNumber());
			accountSummaryResponseDto.setBalance(accountDetails.get().getBalance());
			accountSummaryResponseDto
					.setName(Customer.fullName(customer.get().getFirstName(), customer.get().getLastName()));
			accountSummaryResponseDto.setMessage(Constant.SUCCESS);
			accountSummaryResponseDto.setStatusCode(Constant.ACCEPTED);
			transactions.forEach(transaction -> {
				TransactionDto transactionDto = new TransactionDto();
				transactionDto.setTransactionAmount(transaction.getTransactionAmount());
				transactionDto.setTransactionDate(transaction.getTransactionDate());
				transactionDto.setTransactionDescription(transaction.getTransactionDescription());
				transactionDto.setTransactionType(transaction.getTransactionType());
				transactionDto.setTransactionId(transaction.getTransactionId());
				transactionsList.add(transactionDto);
			});
			accountSummaryResponseDto.setTransactions(transactionsList);

		}

		return accountSummaryResponseDto;
	}

}