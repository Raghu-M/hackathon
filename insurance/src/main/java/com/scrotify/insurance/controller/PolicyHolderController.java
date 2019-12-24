package com.scrotify.insurance.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.service.PolicyHolderService;

/**
 * This method is for the details of the policy holder.
 * 
 * @author anisha.R
 * 
 *
 */
@RestController
@RequestMapping("/policyHolders")
@CrossOrigin
public class PolicyHolderController {

	private static final Logger logger = LogManager.getLogger(PolicyHolderController.class);

	@Autowired
	PolicyHolderService policyHolderService;

	/**
	 * 
	 * @author anisha.R
	 * @param policyHolderId In this method we are passing the policyHolderId, to
	 *                       know the details of the policies under the policyId.
	 * 
	 * @return myPolicyDto In this method we are getting the list of policies of
	 *         particular policyHolder
	 * 
	 */

	@GetMapping()
	public ResponseEntity<List<Policy>> getListOfMyPolicies(Integer policyHolderId) {
		logger.info("Get list of policy details");
		List<Policy> policy = policyHolderService.viewMyPolicy(policyHolderId);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	}

}
