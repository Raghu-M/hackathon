package com.claim.medical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.Hospital;
import com.claim.medical.repository.HospitalRepository;

/**
 * This service is having all the implementations of methods of the hospital.
 * 
 * @author Raghu M
 */
@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * This method is used to fetch all hospitals.
	 * 
	 * @return This will return a list of hospitals along with status code.
	 */
	@Override
	public List<Hospital> getHospitals() {
		return hospitalRepository.findAll();
	}

}
