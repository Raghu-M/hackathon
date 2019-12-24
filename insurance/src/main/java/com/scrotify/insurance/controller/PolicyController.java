package com.scrotify.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.insurance.dto.PolicyResponseDto;
import com.scrotify.insurance.dto.TrendingResponseDto;
import com.scrotify.insurance.service.PolicyService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller has a method has the implementations of the policies.
 * 
 * @Api getTrendingPolicies which is used to get the trending policies
 *      considering the last ten purchase.
 * 
 * @author Anisha R
 * @author Raghu M.
 * 
 */

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/policies")
public class PolicyController {

	/**
	 * This will inject all the implementations in the policyService.
	 */

	@Autowired
	PolicyService policyService;

	/**
	 * This API is used to get the trending policies considering the last ten
	 * purchase.
	 * 
	 * @return This returns the list of trending policies which contain policy name
	 *         and number of policies purchased with httpStatus code 200.
	 * 
	 * @author Raghu M
	 */

	@GetMapping("/trending")
	public ResponseEntity<List<TrendingResponseDto>> getTrendingPolicies() {
		log.info("getTrendingPolicies controller : getting trending policies");
		return ResponseEntity.ok().body(policyService.getTrendingPolicies());
	}

	/**
	 * @author anisha.R This method is used to get the list of all policies
	 *         available
	 * 
	 * @return PolicyResponseDto This response will return the list of policies
	 *         available
	 * 
	 */
	@GetMapping()
	public ResponseEntity<List<PolicyResponseDto>> getListOfAllPolicies() {
		log.info("Get list of policy details");
		List<PolicyResponseDto> policyResponseDto = policyService.listAllPolicies();
		return new ResponseEntity<>(policyResponseDto, HttpStatus.OK);
	}

}
