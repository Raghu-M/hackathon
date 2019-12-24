package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;

/**
 * This service is having all the methods of the account.
 */
public interface AccountService {

	/**
	 * This method is used get all accountNumbers.
	 * 
	 * @param accountNumber. This is the account number of the current customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	AccountResponseDto getAllAccountNumber(Long accountNumber);
	AccountSummaryResponseDto accountSummary(Integer customerId);

}