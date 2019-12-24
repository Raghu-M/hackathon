package com.cassini.beneficiarymanagement.service;

import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

public interface CustomerService {
	Customer authenticateCustomer(LoginRequestDto loginRequestDto) throws UserNotFoundException;
}
