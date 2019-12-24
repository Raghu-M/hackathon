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

import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.repository.ReligionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReligionServiceTest {
	
	@InjectMocks
	ReligionServiceImpl religionServiceImpl;
	
	@Mock
	ReligionRepository religionRepository;
	
	Religion religion = null;
	List<Religion> religions = null;
	@Test
	public void testReligionLists() {
		religion = new Religion();
		religion.setReligionId(1);
		religion.setReligionName("Hindu");
	    religions = new ArrayList<Religion>();
		religions.add(religion);
		Mockito.when(religionRepository.findAll()).thenReturn(religions);
		religions = religionServiceImpl.getReligions();
		assertNotNull(religions);
	}

}
