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

import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.service.OccupationService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OccupationControllerTest {

	@InjectMocks
	OccupationController occupationController;

	@Mock
	OccupationService occupationService;

	@Test
	public void testOccupationList() {
		Mockito.when(occupationService.getOccupations()).thenReturn(new ArrayList<>());
		ResponseEntity<List<Occupation>> result = occupationController.getOccupations();
		assertNotNull(result);
	}

}
