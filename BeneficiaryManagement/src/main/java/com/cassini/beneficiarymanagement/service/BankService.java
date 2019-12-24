package com.cassini.beneficiarymanagement.service;

import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;

public interface BankService {

	public Bank getBankDetails(String ifscCode) throws BankNotFound;

}
