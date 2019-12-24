package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.repository.OccupationRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OccupationServiceTest {
	
	@InjectMocks
	OccupationServiceImpl occupationServiceImpl;
	
	@Mock
	OccupationRepository occupationRepository;
	
	@Test
	public void testOccupationLists() {
		Occupation occupation =new Occupation();
		occupation.setOccupationId(1);
		occupation.setOccupationName("Engineer");
		List<Occupation> occupations = new ArrayList<Occupation>();
		occupations.add(occupation);
		Mockito.when(occupationRepository.findAll()).thenReturn(occupations);
		List<Occupation> occupations2 = occupationServiceImpl.getOccupations();
		assertNotNull(occupations2);
	}

}
