package com.matrimony.cassini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.repository.ReligionRepository;

@Service
public class ReligionServiceImpl implements ReligionService {

	@Autowired
	ReligionRepository religionRepository;

	@Override
	public List<Religion> getReligions() {
		return religionRepository.findAll();
	}

}
