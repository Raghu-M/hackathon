package com.matrimony.cassini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.repository.OccupationRepository;

@Service
public class OccupationServiceImpl implements OccupationService {

	@Autowired
	OccupationRepository occupationRepository;

	@Override
	public List<Occupation> getOccupations() {
		return occupationRepository.findAll();
	}

}
