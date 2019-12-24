package com.cassini.beneficiarymanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.service.BankService;

/**
 * This Controller has a method has the implementations of the bank.
 * 
 * @Api getBankDetails which will get the bank details.
 * 
 * @author Yoganand.
 */
@RestController
@CrossOrigin
@RequestMapping("/banks")
public class BankController {

	private static final Logger logger = LoggerFactory.getLogger(BankController.class);
	/**
	 * This will inject all the implementations in the bankService
	 */

	@Autowired
	private BankService bankService;

	/**
	 * This API is used to get the bank details
	 * 
	 * @param ifscCode.By passing ifsc code as a path variable the bank details
	 *                    could be fetched from the database
	 * 
	 * @return This returns the bank details
	 * @throws BankNotFound This exception is occurs when bank not found while
	 *                      searching banks
	 */

	@GetMapping("/{ifscCode}")
	public ResponseEntity<Bank> getBankDetails(@PathVariable("ifscCode") String ifscCode) throws BankNotFound {
		logger.info("getBankDetails method - getting bank details ");
		return ResponseEntity.ok().body(bankService.getBankDetails(ifscCode));

	}

}
