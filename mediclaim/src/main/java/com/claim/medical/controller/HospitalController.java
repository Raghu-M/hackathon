package com.claim.medical.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.entity.Hospital;
import com.claim.medical.service.HospitalService;

/**
 * This Controller is having the hospital related functionalities. This
 * Controller will call the hospitalService in which the implementations are
 * done.
 * 
 * @API It has a method gethospitals which will fetch all the hospital list.
 * @author RAGHU M
 */
@RestController
@RequestMapping("/hospitals")
@CrossOrigin
public class HospitalController {

	@Autowired
	HospitalService hospitalService;

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	/**
	 * This API is used to fetch all hospitals.
	 * 
	 * @return This will return a list of hospitals along with status code.
	 */
	@GetMapping("")
	public ResponseEntity<List<Hospital>> getHospitals() {

		logger.info("fetching list of hospitals");
		return ResponseEntity.ok().body(hospitalService.getHospitals());
	}

}
