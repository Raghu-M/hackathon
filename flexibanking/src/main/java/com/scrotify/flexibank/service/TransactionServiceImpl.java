package com.scrotify.flexibank.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.FundTransferRequestDto;
import com.scrotify.flexibank.dto.FundTransferResponseDto;
import com.scrotify.flexibank.dto.TransactionRequestDto;
import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.entity.Transaction;
import com.scrotify.flexibank.exception.CreditCardLimitException;
import com.scrotify.flexibank.exception.InvalidCreditCardDetailsException;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CreditCardRepository;
import com.scrotify.flexibank.repository.TransactionRepository;

/**
 * this class will have all the implementations of transaction.
 * 
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	/**
	 * this method will take care of transferring funds from savings account to
	 * mortgage account
	 * 
	 * @param fundTransferRequestDto which includes
	 *                               fromAccountNumber,toAccountNumber,amount and
	 *                               description
	 * @return fundTransferResponseDto which includes statusMessage and statusCode.
	 * @throws InvalidCreditCardDetailsException
	 * @throws CreditCardLimitException 
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Override
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto)
			throws InvalidCreditCardDetailsException, CreditCardLimitException {
		
		FundTransferResponseDto fundTransferResponseDto = new FundTransferResponseDto();
		Optional<Account> toAccount = accountRepository.findById(fundTransferRequestDto.getToAccount());
		Optional<CreditCard> creditCard = creditCardRepository.findById(fundTransferRequestDto.getCreditCardNumber());
		if (!(creditCard.isPresent() && creditCard.get().getCvv().equals(fundTransferRequestDto.getCvv())
				&& creditCard.get().getPin().equals(fundTransferRequestDto.getPin())
				&& creditCard.get().getExpiryDate().isAfter(fundTransferRequestDto.getExpiryDate()))) {
			logger.error("InvalidCreditCardDetailsException occured ");
			throw new InvalidCreditCardDetailsException(Constant.CREDIT_CARD_NOT_FOUND);
			
		} else if (creditCard.get().getCardLimit() < fundTransferRequestDto.getTransactionAmount()
				- creditCard.get().getAccount().getBalance()) {
			logger.error("CreditCardLimitException occured ");
			throw new CreditCardLimitException(Constant.CREDIT_CARD_LIMIT);
		}

		if (!toAccount.isPresent()) {
			logger.info("payee account not found");
			fundTransferResponseDto.setMessage(Constant.NO_PAYEE_ACCOUNT);
			fundTransferResponseDto.setStatusCode(Constant.NOT_ACCEPTABLE);

		} else if (fundTransferRequestDto.getTransactionAmount() <= 0) {
			logger.info("transaction failed, transferring invalid amount");
			fundTransferResponseDto.setMessage(Constant.TRANSACTION_FAILURE);
			fundTransferResponseDto.setStatusCode(Constant.INVALID_AMOUNT);
		} else {
			creditCard.get().getAccount().setBalance(
					creditCard.get().getAccount().getBalance() - fundTransferRequestDto.getTransactionAmount());
			accountRepository.save(creditCard.get().getAccount());
			Transaction fromTransaction = new Transaction();
			fromTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
			fromTransaction.setTransactionType(Constant.DEBIT_TRANSACTION);
			fromTransaction.setTransactionDate(LocalDate.now());
			fromTransaction
					.setTransactionDescription("transacted to account number " + fundTransferRequestDto.getToAccount()
							+ " message : " + fundTransferRequestDto.getTransactionDescription());
			fromTransaction.setAccount(creditCard.get().getAccount());
			transactionRepository.save(fromTransaction);
			Transaction toTransaction = new Transaction();
			toTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
			toTransaction.setTransactionType(Constant.CREDIT_TRANSACTION);
			toTransaction.setTransactionDate(LocalDate.now());
			toTransaction.setTransactionDescription(
					"transacted from account number " + creditCard.get().getAccount().getAccountNumber() + " message : "
							+ fundTransferRequestDto.getTransactionDescription());
			toTransaction.setAccount(toAccount.get());
			transactionRepository.save(toTransaction);
			toAccount.get().setBalance(toAccount.get().getBalance() + fundTransferRequestDto.getTransactionAmount());
			accountRepository.save(toAccount.get());
			fundTransferResponseDto.setMessage(Constant.TRANSACTION_SUCCESS);
			fundTransferResponseDto.setStatusCode(Constant.ACCEPTED);
			logger.info("transaction successfull");
		}
		return fundTransferResponseDto;
	}

	/**
	 * This API is used to get Transactions by passing transactionRequestDto
	 * TransactionRequestDto includes AccountNumber,month and year This returns the
	 * transactions
	 */
	@Override
	public List<Transaction> getTransactions(TransactionRequestDto transactionRequestDto) {
		logger.info("to get monthly transactions");
		List<Transaction> transactions = null;
		if (transactionRequestDto.getMonth() == null) {
			Account account = new Account();
			account.setAccountNumber(transactionRequestDto.getAccountNumber());
			transactions = transactionRepository.findTop5ByAccountOrderByTransactionIdDesc(account);
		} else {

			String month = String.format("%02d", transactionRequestDto.getMonth());
			Integer year = transactionRequestDto.getYear();
			LocalDate endDate = Year.parse(transactionRequestDto.getYear().toString())
					.atMonth(transactionRequestDto.getMonth()).atEndOfMonth();
			LocalDate startDate = LocalDate.parse(year + "-" + month + "-" + "01");

			Account account = new Account();
			account.setAccountNumber(transactionRequestDto.getAccountNumber());
			transactions = transactionRepository.getAllByAccountAndTransactionDateBetween(account, startDate, endDate);
		}

		return transactions;
	}

}
