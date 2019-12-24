package com.scrotify.insurance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.insurance.dto.PolicyDetailsResponseDto;
import com.scrotify.insurance.service.PolicyDetailsService;
import com.scrotify.insurance.utils.ApiConstant;

/**
 * @author Vasavi
 * @since 2019-12-20 This class is used to get details of the policy based on
 *        policyId.
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/insurance")
public class PolicyDetailsController {
	public static final Logger logger = LoggerFactory.getLogger(PurchasePolicyController.class);
	@Autowired
	PolicyDetailsService policyDetailsService;

	/**
	 * This method is used to get details of the policy based on policyId.
	 * 
	 * @param policyId
	 * @return PolicyDetailsResponseDto
	 */
	@GetMapping("/policies/{policyId}")
	public ResponseEntity<PolicyDetailsResponseDto> getPolicyDetails(@PathVariable Integer policyId) {
		logger.info("Inside PolicyDetailsController : getPolicyDetails");
		PolicyDetailsResponseDto policyDetailsResponseDto = policyDetailsService.getPolicyDetails(policyId);
		if (policyId != null) {
			logger.info("policyId exits");
			policyDetailsResponseDto.setMessage(ApiConstant.SUCCESS);
			policyDetailsResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
			return new ResponseEntity<>(policyDetailsResponseDto, HttpStatus.OK);
		} else {
			logger.info("policyId does not exists");
			policyDetailsResponseDto.setMessage(ApiConstant.FAILED);
			policyDetailsResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
			return new ResponseEntity<>(policyDetailsResponseDto, HttpStatus.NOT_FOUND);
		}

	}

}
