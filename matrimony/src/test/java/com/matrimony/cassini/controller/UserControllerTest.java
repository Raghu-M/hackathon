package com.matrimony.cassini.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@Mock
	private UserServiceImpl userServiceImpl;

	@InjectMocks
	private UserController userController;

	LoginRequestDto loginRequestDto = null;
	User user = null;

	@Test
	public void testUserLogin() throws UserNotFoundException {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("yoga");
		loginRequestDto.setPassword("india");

		user = new User();
		user.setUserName("yoga");
		user.setPassword("india");
		Mockito.when(userServiceImpl.userLogin(loginRequestDto)).thenReturn(Optional.of(user));
		ResponseEntity<Optional<User>> user = userController.userLogin(loginRequestDto);
		assertNotNull(user);

	}

}
