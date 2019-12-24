package com.claim.medical.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.ApproveClaimRequestDto;
import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.entity.Claim;
import com.claim.medical.exception.AlreadyClaimedException;
import com.claim.medical.exception.InvalidClaimAmountException;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyHolderNotFoundException;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.service.ClaimService;

/**
 * This Controller is having the claim related functionalities. This Controller
 * will call the claimService in which the implementations are done.
 * 
 * @API It has a method raiseClaim which will raise the claim request.
 * @author RAGHU M
 */

@RestController
@RequestMapping("/claims")
@CrossOrigin
public class ClaimController {
	@Autowired
	ClaimService claimService;

	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	/**
	 * @param claimId
	 * @return claimDetailsResponseDto will contain the status and approval flow of
	 *         a claim request
	 * @throws PolicyNotFoundException
	 */
	@GetMapping("/{claimId}")
	public ResponseEntity<Claim> claimDetails(@RequestParam("claimId") Long claimId) throws PolicyNotFoundException {
		return ResponseEntity.ok().body(claimService.viewClaimDetails(claimId));
	}

	/**
	 * 
	 * @param claimRequestDto which includes policyholder details and hospital
	 *                        details
	 * @return This returns the request details
	 * @throws PolicyHolderNotFoundException
	 * This exception occurs when policyholderis not found while claiming the request
	 * @throws PolicyExpiredException        This exception occurs when policy gets
	 * @throws InvalidClaimAmountException   This exception arises when policy
	 * @throws PolicyNotFoundException       This exception arises when policy is
	 * @throws AlreadyClaimedException
	 */
	@PostMapping
	public ResponseEntity<Long> raiseClaim(@RequestBody ClaimRequestDto claimRequestDto)
			throws PolicyHolderNotFoundException, PolicyExpiredException, InvalidClaimAmountException,
			PolicyNotFoundException, AlreadyClaimedException {
		logger.info("raising claim");
		return ResponseEntity.ok().body(claimService.raiseRequest(claimRequestDto));

	}

	@GetMapping("/request/{userId}")
	public ResponseEntity<List<Claim>> getClaims(@PathVariable Integer userId) throws UserNotFoundException {
		return ResponseEntity.ok().body(claimService.getClaims(userId));
	}

	@PutMapping
	public ResponseEntity<String> approverClaimResponse(@RequestBody ApproveClaimRequestDto approveClaimRequestDto) {
		return ResponseEntity.ok().body(claimService.approverClaimResponse(approveClaimRequestDto));
	}

}
