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

import com.claim.medical.entity.Ailment;
import com.claim.medical.service.AilmentService;

/**
 * This Controller is having the ailment related functionalities. This
 * Controller will call the ailmentService in which the implementations are
 * done.
 * 
 * @API It has a method getAilments which will fetch all the ailments.
 * @author RAGHU M
 */
@RestController
@RequestMapping("/ailments")
@CrossOrigin
public class AilmentController {

	@Autowired
	AilmentService ailmentService;
	private static final Logger logger = LoggerFactory.getLogger(AilmentController.class);

	/**
	 * This API is used to fetch all aliments.
	 * 
	 * @return This will return a list of ailments along with status code.
	 */
	@GetMapping
	public ResponseEntity<List<Ailment>> getAilments() {
		logger.info("fetching ailment list");
		return ResponseEntity.ok().body(ailmentService.getAilments());
	}

}
