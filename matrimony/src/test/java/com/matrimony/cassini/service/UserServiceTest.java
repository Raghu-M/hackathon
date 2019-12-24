package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	LoginRequestDto loginRequestDto = null;
	User user = null;

	@Test
	public void testuserLogin() throws UserNotFoundException {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("yoga");
		loginRequestDto.setPassword("india");

		user = new User();
		user.setUserName("yoga");
		user.setPassword("india");

		Mockito.when(userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(user));
		Optional<User> users = userServiceImpl.userLogin(loginRequestDto);
		assertNotNull(users);
	}

}
