package com.matrimony.cassini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.dto.UserAcceptanceRequestDto;
import com.matrimony.cassini.dto.UserResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserInterestService;

@RestController
@RequestMapping("/interests")
@CrossOrigin
public class UserInterestController {

	@Autowired
	UserInterestService userInterestService;

	@GetMapping("/{userId}/accepted")
	public ResponseEntity<List<User>> acceptedDetails(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok().body(userInterestService.acceptedDetails(userId));

	}

	@PostMapping
	public ResponseEntity<List<User>> getAllFilteredUsers(@RequestBody FilterRequestDto filterRequestDto) {
		return ResponseEntity.ok().body(userInterestService.getAllFilteredUsers(filterRequestDto));
	}

	@PostMapping("/request")
	public ResponseEntity<InterestResponseDto> showInterest(@RequestBody InterestRequestDto interestRequestDto)
			throws UserNotFoundException {
		return new ResponseEntity<>(userInterestService.showInterest(interestRequestDto), HttpStatus.CREATED);

	}
	
	@GetMapping("/{userId}/interest")
	public ResponseEntity<List<Optional<User>>> getInterestedList(@PathVariable("userId") Integer userId) throws RequestNotRaisedException{
		List<Optional<User>> users =userInterestService.requestList(userId);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<UserResponseDto> userResponse(@RequestBody UserAcceptanceRequestDto userAcceptanceRequestDto) throws RequestNotRaisedException{
		return ResponseEntity.ok().body(userInterestService.userResponse(userAcceptanceRequestDto));
	}
}
