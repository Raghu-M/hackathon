package com.scrotify.flexibank.service;

import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.exception.AccountNotFoundException;
import com.scrotify.flexibank.exception.CreditCardNotFoundException;

public interface CreditCardService {
	
	CreditCard getCreditCard(Long accountNumber) throws AccountNotFoundException, CreditCardNotFoundException;

}
