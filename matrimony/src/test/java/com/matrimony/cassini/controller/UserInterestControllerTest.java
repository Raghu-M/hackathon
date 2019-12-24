package com.matrimony.cassini.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.service.UserInterestService;
import com.matrimony.cassini.service.UserInterestServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInterestControllerTest {

	@Mock
	private UserInterestService userInterestService;
	
	@InjectMocks
	private UserInterestController userInterestController;
	
	User user = null;
	List<User> userlist = null;
	@Test
	public void testAcceptedDetails() {
		userlist = new ArrayList<>();
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
		userlist.add(user);
		Mockito.when(userInterestService.acceptedDetails(1)).thenReturn(userlist);
		ResponseEntity<List<User>> users = userInterestController.acceptedDetails(1);
		assertNotNull(users);
	}
	
	@Test
	public void testInterestList() {
		userlist = new ArrayList<>();
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
		userlist.add(user);
		Mockito.when(user);
	}
	
	@Test
	public void  getAllFilteredUsersTest() {
		Mockito.when(userInterestService.getAllFilteredUsers(Mockito.any())).thenReturn(new ArrayList<User>());
		assertNotNull(userInterestController.getAllFilteredUsers(new FilterRequestDto()));
	}
	
	@Test
	public void getInterestedListTest() throws RequestNotRaisedException {
		Mockito.when(userInterestService.requestList(Mockito.any())).thenReturn(new ArrayList<>());
		assertNotNull(userInterestController.getInterestedList(1));
	}
}
