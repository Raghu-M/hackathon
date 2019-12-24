package com.claim.medical.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medical.service.AilmentService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AilmentControllerTest {
	@Mock
	AilmentService ailmentService;
	@InjectMocks
	AilmentController ailmentController;

	@Test
	public void testGetAilments() {
		Mockito.when(ailmentService.getAilments()).thenReturn(new ArrayList<>());
		assertNotNull(ailmentController.getAilments());

	}

}
