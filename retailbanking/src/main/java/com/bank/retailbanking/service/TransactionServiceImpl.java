package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionListResponseDTO;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.TransactionRepository;

/**
 * this class will have all the implementations of transaction.
 * 
 * @author Raghu M
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * this method will take care of transferring funds from one account to other
	 * account
	 * 
	 * @param fundTransferRequestDto which includes payer accountNumber, payee
	 *                               accountNumber, transaction amount and
	 *                               description.
	 * @return fundTransferResponseDto which includes statusMessage and statusCode.
	 */
	@Override
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto) {
		FundTransferResponseDto fundTransferResponseDto = new FundTransferResponseDto();
		Account fromAccount = accountRepository.findById(fundTransferRequestDto.getFromAccount()).orElse(null);
		Account toAccount = accountRepository.findById(fundTransferRequestDto.getToAccount()).orElse(null);
		if (fromAccount == null) {
			logger.info("payer account not found");
			fundTransferResponseDto.setMessage(Constant.NO_PAYER_ACCOUNT);
			fundTransferResponseDto.setStatusCode(Constant.NOT_ACCEPTABLE);
		} else if (toAccount == null) {
			logger.info("payee account not found");
			fundTransferResponseDto.setMessage(Constant.NO_PAYEE_ACCOUNT);
			fundTransferResponseDto.setStatusCode(Constant.NOT_ACCEPTABLE);
			
		} else if (fundTransferRequestDto.getTransactionAmount() <= 0) {
			logger.info("transaction failed, transferring invalid amount");
			fundTransferResponseDto.setMessage(Constant.TRANSACTION_FAILURE);
			fundTransferResponseDto.setStatusCode(Constant.INVALID_AMOUNT);
		}

		else if (fundTransferRequestDto.getTransactionAmount() + Constant.MINIMUM_BALANCE > fromAccount.getBalance()) {
			logger.info("transaction failed insufficient balance");
			fundTransferResponseDto.setMessage(Constant.TRANSACTION_FAILURE);
			fundTransferResponseDto.setStatusCode(Constant.NOT_ACCEPTABLE);

		} else {
			fromAccount.setBalance(fromAccount.getBalance() - fundTransferRequestDto.getTransactionAmount());
			accountRepository.save(fromAccount);
			Transaction fromTransaction = new Transaction();
			fromTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
			fromTransaction.setTransactionType(Constant.DEBIT_TRANSACTION);
			fromTransaction.setTransactionDate(LocalDate.now());
			fromTransaction
					.setTransactionDescription("transacted to account number " + fundTransferRequestDto.getToAccount()
							+ " message : " + fundTransferRequestDto.getTransactionDescription());
			fromTransaction.setAccount(fromAccount);
			transactionRepository.save(fromTransaction);
			Transaction toTransaction = new Transaction();
			toTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
			toTransaction.setTransactionType(Constant.CREDIT_TRANSACTION);
			toTransaction.setTransactionDate(LocalDate.now());
			toTransaction.setTransactionDescription(
					"transacted from account number " + fundTransferRequestDto.getFromAccount() + " message : "
							+ fundTransferRequestDto.getTransactionDescription());
			toTransaction.setAccount(toAccount);
			transactionRepository.save(toTransaction);
			toAccount.setBalance(toAccount.getBalance() + fundTransferRequestDto.getTransactionAmount());
			accountRepository.save(toAccount);
			fundTransferResponseDto.setMessage(Constant.TRANSACTION_SUCCESS);
			fundTransferResponseDto.setStatusCode(Constant.ACCEPTED);
			logger.info("transaction successfull");

		}
		return fundTransferResponseDto;
	}

	/**
	 * This API is used to get monthlyTransactions which will find all the
	 * accountNumbers and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */

	@Override
	public TransactionResponseDto monthlyTransactions(TransactionRequestDto transactionRequestDto) {
		logger.info("to get monthly transactions");
		String month = String.format("%02d", transactionRequestDto.getMonth());
		Integer year = transactionRequestDto.getYear();
		LocalDate endDate = Year.parse(transactionRequestDto.getYear().toString()).atMonth(transactionRequestDto.getMonth()).atEndOfMonth();
		LocalDate startDate = LocalDate.parse(year+"-"+month+"-"+"01");

		Account account = new Account();
		account.setAccountNumber(transactionRequestDto.getAccountNumber());
		List<Transaction> transactions = transactionRepository.getAllByAccountAndTransactionDateBetween(account,
				startDate, endDate);
		List<TransactionListResponseDTO> transactionListResponseDTOList = new ArrayList<>();
		transactions.forEach(transaction -> {
			TransactionListResponseDTO transactionListResponseDTO = new TransactionListResponseDTO();
			transactionListResponseDTO.setTransactionType(transaction.getTransactionType());
			transactionListResponseDTO.setTransactionDate(transaction.getTransactionDate());
			transactionListResponseDTO.setTransactionAmount(transaction.getTransactionAmount());
			transactionListResponseDTO.setTransactionDescription(transaction.getTransactionDescription());
			transactionListResponseDTO.setTransactionId(transaction.getTransactionId());
			transactionListResponseDTOList.add(transactionListResponseDTO);
		});
		TransactionResponseDto transactionResponseDTO = new TransactionResponseDto();
		transactionResponseDTO.setTransactionListResponseDTO(transactionListResponseDTOList);
		transactionResponseDTO.setStatuscode(Constant.ACCEPTED);
		transactionResponseDTO.setMessage(Constant.SUCCESS);

		return transactionResponseDTO;
	}

}
