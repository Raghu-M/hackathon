package com.scrotify.insurance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.insurance.dto.PurchasePolicyRequestDto;
import com.scrotify.insurance.dto.PurchasePolicyResponseDto;
import com.scrotify.insurance.service.PurchasePolicyService;
import com.scrotify.insurance.utils.ApiConstant;

/**
 * @author Vasavi
 * @since 2019-12-20 This class is used for to buy a policy
 *
 */
@RestController
@CrossOrigin
@RequestMapping("")
public class PurchasePolicyController {
	public static final Logger logger = LoggerFactory.getLogger(PurchasePolicyController.class);

	@Autowired
	PurchasePolicyService purchasePolicyService;

	/**
	 * This method is used for to buy a policy
	 * 
	 * @param policyId
	 * @param purchasePolicyRequestDto it is the request object which contains
	 *                                 policyId,policyHolderName,gender,email,mobileNumber,nomineeName
	 *                                 and relationShipWithNominee
	 * @return it returns PurchasePolicyResponseDto object which contains message
	 *         and statusCode
	 */
	@PostMapping("/policies/{policyId}/purchasePolicies")
	public ResponseEntity<PurchasePolicyResponseDto> buyPolicy(@PathVariable("policyId") Integer policyId,
			@RequestBody PurchasePolicyRequestDto purchasePolicyRequestDto) {
		logger.info("Purchase policy  controller");
		PurchasePolicyResponseDto purchasePolicyResponseDto = purchasePolicyService.buyPolicy(purchasePolicyRequestDto);
		if (policyId != null) {
			logger.info("policyId exits");
			purchasePolicyResponseDto.setMessage(ApiConstant.SUCCESS);
			purchasePolicyResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
			return new ResponseEntity<>(purchasePolicyResponseDto, HttpStatus.CREATED);
		} else {
			logger.info("policyId does not exists");
			purchasePolicyResponseDto.setMessage(ApiConstant.FAILED);
			purchasePolicyResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
			return new ResponseEntity<>(purchasePolicyResponseDto, HttpStatus.NOT_FOUND);

		}

	}
}
