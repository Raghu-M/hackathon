package com.matrimony.cassini.service;

import java.util.Optional;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNameAlreadyExistException;
import com.matrimony.cassini.exception.UserNotFoundException;

public interface UserService {
	
	public RegisterResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) throws UserNameAlreadyExistException;
	

	public Optional<User> userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
