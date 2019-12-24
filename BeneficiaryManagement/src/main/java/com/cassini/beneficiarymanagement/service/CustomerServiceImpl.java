package com.cassini.beneficiarymanagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.repository.CustomerRepository;

/**
 * This will implements all the methods in the CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * This will inject all the methods in the customerRepository
	 */
	@Autowired
	CustomerRepository customerRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	/**
	 * This method is used authenticate the customer.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException
	 */
	@Override
	public Customer authenticateCustomer(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		logger.info("Entering into authentication Service");
		Optional<Customer> customer = customerRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}

	}

}
