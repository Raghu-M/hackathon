package com.claim.medical.service;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.entity.User;
import com.claim.medical.exception.UserNotFoundException;

/**
 * This service is having all the methods of the User.
 * 
 * @author Yoga
 */
public interface UserService {
	/**
	 * This method is used authenticate the customer.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of List of Claim Requests.
	 * @throws UserNotFoundException
	 */
	public User userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
