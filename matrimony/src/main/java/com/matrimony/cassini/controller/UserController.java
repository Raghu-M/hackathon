package com.matrimony.cassini.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNameAlreadyExistException;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@PostMapping("login")
	public ResponseEntity<Optional<User>> userLogin(@RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		return ResponseEntity.ok().body(userService.userLogin(loginRequestDto));
	}

	@PostMapping
	public ResponseEntity<RegisterResponseDto> saveUser(
			@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) throws UserNameAlreadyExistException {
		return ResponseEntity.ok().body(userService.saveUser(userRegistrationRequestDto));
	}

}
