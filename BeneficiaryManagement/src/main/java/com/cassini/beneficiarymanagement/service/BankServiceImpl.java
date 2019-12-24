package com.cassini.beneficiarymanagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.repository.BankRepository;
/**
 * This will implements all the methods in the BankService
 */

@Service
public class BankServiceImpl implements BankService {

	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	/**
	 * This will inject all the implementations in the bankRepository
	 */

	@Autowired
	BankRepository bankRepository;

	/**
	 * This API is used to get the bank details
	 * 
	 * By passing Ifsc code as a path variable the bank details could be fetched
	 * from the database
	 * 
	 * This returns the bank details BankNotFound This exception is occurs when bank
	 * not found while searching banks
	 */
	@Override
	public Bank getBankDetails(String ifscCode) throws BankNotFound {
		logger.info("getBankDetails service method - getting bank details ");
		Optional<Bank> bankDetaials = bankRepository.findByIfscCode(ifscCode);
		if (bankDetaials.isPresent()) {
			return bankDetaials.get();
		} else {
			throw new BankNotFound(Constant.BANK_NOT_FOUND);
		}
	}
}