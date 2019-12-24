package com.scrotify.flexibank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.exception.AccountNotFoundException;
import com.scrotify.flexibank.exception.CreditCardNotFoundException;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CreditCardRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * This service is having all the implementations of methods of the credit card.
 *
 */
@Service
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {
	

	/**
	 * This will inject all the methods in the creditCardRepository.
	 */
	@Autowired
	CreditCardRepository creditCardRepository;
	

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;
	
	/**
	 * This method is used to get the credit card details.
	 * 
	 * @param accountNumber which is used get the credit cards of that account.
	 * 
	 * @return credit card detail of that account.
	 * @throws CreditCardNotFoundException 
	 * @throws AccountNotFoundException
	 */
	@Override
	public CreditCard getCreditCard(Long accountNumber) throws AccountNotFoundException, CreditCardNotFoundException {
		Optional<Account> account = accountRepository.findById(accountNumber);
		if(!account.isPresent()) {
			log.error("getCreditCard service : AccountNotFoundException occured");
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		Optional<CreditCard> creditCard = creditCardRepository.findByAccount(account.get());
		if(!creditCard.isPresent()) {
			log.error("getCreditCard service : AccountNotFoundException occured");
			throw new CreditCardNotFoundException(Constant.CREDIT_CARD_NOT_FOUND);
		}else {
			
			return creditCard.get();
		}
		
		
	}

}
