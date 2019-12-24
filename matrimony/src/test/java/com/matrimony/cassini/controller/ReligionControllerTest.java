package com.matrimony.cassini.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.service.ReligionService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReligionControllerTest {
	
	@InjectMocks
	ReligionController religionController;
	
	@Mock
	ReligionService religionService;
	
	@Test
	public void testReligionList() {
		Mockito.when(religionService.getReligions()).thenReturn(new ArrayList<>());
		ResponseEntity<List<Religion>> actual = religionController.getReligions();
		assertNotNull(actual);
	}

}
