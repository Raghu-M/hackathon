package com.matrimony.cassini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.service.ReligionService;

@RestController
@RequestMapping("/religions")
@CrossOrigin
public class ReligionController {

	@Autowired
	ReligionService religionService;

	@GetMapping
	public ResponseEntity<List<Religion>> getReligions() {
		return ResponseEntity.ok().body(religionService.getReligions());
	}

}
