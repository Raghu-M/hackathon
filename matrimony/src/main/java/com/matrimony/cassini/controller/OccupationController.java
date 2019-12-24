package com.matrimony.cassini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.service.OccupationService;

@RestController
@RequestMapping("/occupations")
@CrossOrigin
public class OccupationController {

	@Autowired
	OccupationService occupationService;

	@GetMapping
	public ResponseEntity<List<Occupation>> getOccupations() {
		return ResponseEntity.ok().body(occupationService.getOccupations());
	}

}
