package com.claim.medical.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.Ailment;
import com.claim.medical.repository.AilmentRepository;

/**
 * This service is having all the implementations of methods of the Ailments.
 * 
 * @author Raghu M
 */
@Service
public class AilmentServiceImpl implements AilmentService {

	@Autowired
	AilmentRepository ailmentRepository;

	private static final Logger logger = LoggerFactory.getLogger(AilmentServiceImpl.class);

	/**
	 * This method is used to fetch all aliments.
	 * 
	 * @return This will return a list of ailments along with status code.
	 */
	@Override
	public List<Ailment> getAilments() {
		logger.info("fetching hospital list");
		return ailmentRepository.findAll();
	}

}
