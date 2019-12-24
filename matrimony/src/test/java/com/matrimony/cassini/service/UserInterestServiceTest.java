package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mockitoSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.repository.UserInterestRepository;
import com.matrimony.cassini.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInterestServiceTest {

	@Mock
	private UserInterestRepository userInterestRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserInterestServiceImpl userInterestServiceImpl;

	User user = null;
	User user1 = null;
	UserInterest userInterest = null;
	List<UserInterest> userIntrestList = null;

	@Test
	public void testAcceptedDetails() {
		userIntrestList = new ArrayList<>();
		userInterest = new UserInterest();
		userInterest.setFromUser(user1);
		userInterest.setToUser(user);
		userInterest.setMapId(1);
		userInterest.setDate(LocalDate.now());
		userInterest.setStatus("Approved");
		userIntrestList.add(userInterest);
		user = new User();
		user.setUserId(1);
		user.setCity("bng");
		user.setDateOfBirth(LocalDate.now());
		user.setEmail("yoga@gmail.com");
		user.setFullName("yoga");
		user.setGender("male");
		user.setHeight(6.1);
		user.setMotherTongue("tel");
		user.setPassword("india");
		user.setUserName("yogaa");
		user.setOccupation("doc");
		user.setReligion("hind");

		user1 = new User();
		user1.setUserId(1);

		Mockito.when(userInterestRepository.findByFromUserAndStatus(Mockito.any(), Mockito.anyString()))
				.thenReturn(userIntrestList);
		List<User> userslist = userInterestServiceImpl.acceptedDetails(1);
		assertNotNull(userslist);
	}

	@Test
	public void getAllFilteredUsersTest() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserId(1);
		users.add(user);
		List<UserInterest> userInterests = new ArrayList<UserInterest>();
		UserInterest userInterest = new UserInterest();
		userInterest.setFromUser(user);
		userInterest.setToUser(user);
		userInterests.add(userInterest);
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findByGenderNot(Mockito.any())).thenReturn(users);
		Mockito.when(userInterestRepository.findByToUser(Mockito.any())).thenReturn(userInterests);
		Mockito.when(userInterestRepository.findByFromUserAndStatus(Mockito.any(), Mockito.any()))
				.thenReturn(userInterests);
		assertNotNull(userInterestServiceImpl.getAllFilteredUsers(new FilterRequestDto()));
	}

	@Test
	public void requestListTset() throws RequestNotRaisedException {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserId(1);
		users.add(user);
		List<UserInterest> userInterests = new ArrayList<UserInterest>();
		UserInterest userInterest = new UserInterest();
		userInterest.setFromUser(user);
		userInterest.setToUser(user);
		userInterests.add(userInterest);
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		Mockito.when(userInterestRepository.findAllUserMappingsByToUserAndStatus(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<UserInterest>());
		assertNotNull(userInterestServiceImpl.requestList(1));
		Mockito.when(userInterestRepository.findAllUserMappingsByToUserAndStatus(Mockito.any(), Mockito.any()))
		.thenReturn(userInterests);
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		assertNotNull(userInterestServiceImpl.requestList(1));
		
	}
}
