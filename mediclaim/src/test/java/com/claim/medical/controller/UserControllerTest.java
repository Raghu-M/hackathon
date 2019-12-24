package com.claim.medical.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.entity.Role;
import com.claim.medical.entity.User;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private UserServiceImpl userServiceImpl;

	@InjectMocks
	private UserController userController;

	LoginRequestDto loginRequestDto = null;

	@Test
	public void userLoginTest() throws UserNotFoundException {
		User user = new User();
		user.setUserName("yoga");
		user.setPassword("india");
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("Level1");
		Mockito.when(userServiceImpl.userLogin(loginRequestDto)).thenReturn(user);
		ResponseEntity<User> users = null;
		try {
			users = userController.userLogin(loginRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(users);
	}

}
