package com.claim.medical.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medical.repository.AilmentRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AilmentServiceTest {
	
	@Mock
	 AilmentRepository ailmentRepository;
	@InjectMocks
	AilmentServiceImpl ailmentServiceImpl;
	
	@Test
	public void testGetAilments() {
		Mockito.when(ailmentRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(ailmentServiceImpl.getAilments());
	}

}
