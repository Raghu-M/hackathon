package com.claim.medical.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.entity.User;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.service.UserService;

/**
 * This Controller is having the User related functionalities. This Controller
 * will call the CustomerService in which the implementations are done.
 * 
 * @API It has a method authenticateUsers which will authenticate the user.
 * @author Yoga
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * This will inject all the implementations in the UserService.
	 */
	@Autowired
	private UserService userService;

	/**
	 * This API has a method authenticateCustomer which will authenticate the user.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of Claims and the List of Claim Requests.
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<User> userLogin(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException {
		return ResponseEntity.ok().body(userService.userLogin(loginRequestDto));

	}
}