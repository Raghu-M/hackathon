package com.cassini.beneficiarymanagement.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.IfscNotMatchedException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

/**
 * This Controller has a method has the implementations of the beneficiaries.
 * 
 * @Api getAllBeneficiaries which will get all the beneficiaries.
 * @Api addBeneficiaries which will add the beneficiaries.
 * @Api updateBeneficiaries which will update the beneficiaries.
 * @Api deleteBeneficiaries which will delete the beneficiaries.
 * 
 * @author PriyaDharshini S
 * @author Raghu M.
 * @author Yoganand.
 * @author Nivetha.
 */
@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
	/**
	 * This will inject all the implementations in the beneficiaryService
	 */

	@Autowired
	BeneficiaryService beneficiaryService;

	/**
	 * This API is used to list all the beneficiary details by passing the
	 * customerId
	 * 
	 * @param customerId.By passing the customerId as a pathvariable the
	 *                      beneficiaries of current customer can be listed which
	 *                      includes both customer details and the beneficiary
	 *                      details
	 * 
	 * @return This returns the list of beneficiaries
	 */

	@GetMapping("/{customerId}")
	public ResponseEntity<List<BeneficiaryListDto>> getAllBeneficiary(@PathVariable("customerId") Integer customerId) {
		logger.info("getAllBeneficiary method - getting all beneficieries ");
		return new ResponseEntity<>(beneficiaryService.getAllBeneficiary(customerId), HttpStatus.OK);
	}

	/**
	 * This API is used to add the beneficiary by passing the
	 * addBeneficiaryRequestDto
	 * 
	 * @param addBeneficiaryRequestDto is a requestDto which includes
	 *                                 customerId,beneficiaryName,beneficiaryAccountNumber
	 *                                 and ifsc code
	 * 
	 * @return This returns the new beneficiary details
	 * 
	 * @throws AccountNotFoundException         This exception occurs when account
	 *                                          does not found
	 * 
	 * @throws MaximumBeneficiaryException      This exception occurs when
	 *                                          beneficiary list exceeds the limit
	 *                                          of 10 beneficiaries
	 * 
	 * @throws UserNotFoundException            This exception occurs when user does
	 *                                          not found while adding beneficiary
	 * @throws BeneficiaryAlreadyExistException This exception occurs when
	 *                                          beneficiary already exists while
	 *                                          adding new one
	 */

	@PostMapping
	public MessageDto addBeneficiary(@RequestBody AddBeneficiaryRequestDto addBeneficiaryRequestDto)
			throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException,
			BeneficiaryAlreadyExistException {
		logger.info("addBeneficiary method - adding beneficiries ");
		return beneficiaryService.addBeneficiary(addBeneficiaryRequestDto);
	}

	/**
	 * This API is used to update the beneficiary details by passing the
	 * updateBeneficiaryRequestDto
	 * 
	 * @param updateBeneficiaryRequestDto includes beneficiaryName and beneficiaryId
	 * @return This returns the new edited beneficiary details
	 * 
	 * @throws BeneficiaryNotFoundException This exception occurs when beneficiary
	 *                                      does not found while editing the
	 *                                      beneficiaryDetails
	 */

	@PutMapping
	public MessageDto updateBeneficiary(@RequestBody UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto)
			throws BeneficiaryNotFoundException, AccountNotFoundException, IfscNotMatchedException {
		logger.info("updateBeneficiary method - updating beneficiery ");
		return beneficiaryService.updateBeneficiary(updateBeneficiaryRequestDto);
	}

	/**
	 * This API is used to delete the beneficiary by passing the beneficiaryId
	 * 
	 * @param beneficiaryId.By passing the beneficiaryId the unwanted beneficiary
	 *                         can be deleted
	 * 
	 * @return This returns the messageDto which includes message and statuscode
	 * @throws BeneficiaryNotFoundException This exception occurs when beneficiary
	 *                                      does not found
	 */

	@DeleteMapping("/{beneficiaryId}")
	public ResponseEntity<MessageDto> deleteBeneficiary(@PathVariable("beneficiaryId") Integer beneficiaryId)
			throws BeneficiaryNotFoundException {
		logger.info("deleteBeneficiary method - deleting beneficiery ");
		return ResponseEntity.ok().body(beneficiaryService.deleteBeneficiary(beneficiaryId));

	}

}
