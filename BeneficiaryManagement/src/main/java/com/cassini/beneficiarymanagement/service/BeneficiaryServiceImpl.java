package com.cassini.beneficiarymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.IfscNotMatchedException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.repository.AccountRepository;
import com.cassini.beneficiarymanagement.repository.BeneficiaryRepository;
import com.cassini.beneficiarymanagement.repository.CustomerRepository;

/**
 * This will implements all the methods in the BeneficiaryService
 */
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

	/**
	 * This will inject all the implementations in the repository classes
	 */

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	/**
	 * This will inject all the methods in the customerRepository
	 */
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * This will inject all the methods in the accountRepository
	 */
	@Autowired
	AccountRepository accountRepository;

	/**
	 * This API is used to list all the beneficiary details by passing the
	 * customerId
	 * 
	 * By passing the customerId as a path variable the beneficiaries of current
	 * customer can be listed which includes both customer details and the
	 * beneficiary details
	 * 
	 * This returns the list of beneficiaries
	 */

	@Override
	public List<BeneficiaryListDto> getAllBeneficiary(Integer customerId) {
		logger.info("getAllBeneficiary service method - getting beneficieries ");
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		List<Beneficiary> beneficiaries = beneficiaryRepository
				.findAllByCustomerAndStatusOrderByBeneficiaryNameAsc(customer, Constant.ACTIVE);
		List<BeneficiaryListDto> beneficiaryListDtos = new ArrayList<>();
		beneficiaries.forEach(beneficiarie -> {
			BeneficiaryListDto beneficiaryListDto = new BeneficiaryListDto();
			beneficiaryListDto.setAccountNumber(beneficiarie.getBeneficiaryAccount().getAccountNumber());
			beneficiaryListDto.setBankName(beneficiarie.getBeneficiaryAccount().getBank().getBankName());
			beneficiaryListDto.setBeneficiaryName(beneficiarie.getBeneficiaryName());
			beneficiaryListDto.setBranchName(beneficiarie.getBeneficiaryAccount().getBank().getBranchName());
			beneficiaryListDto.setBeneficiaryId(beneficiarie.getBeneficiaryId());
			beneficiaryListDto.setIfscCode(beneficiarie.getBeneficiaryAccount().getBank().getIfscCode());
			beneficiaryListDtos.add(beneficiaryListDto);
		});
		return beneficiaryListDtos;

	}

	/**
	 * This API is used to add the beneficiary by passing the
	 * addBeneficiaryRequestDto
	 * 
	 * addBeneficiaryRequestDto is a requestDto which includes
	 * customerId,beneficiaryName,beneficiaryAccountNumber and ifsc code
	 * 
	 * This returns the new beneficiary details
	 * 
	 * AccountNotFoundException .This exception occurs when account does not found
	 * 
	 * It throws MaximumBeneficiaryException. This exception occurs when beneficiary
	 * list exceeds the limit of 10 beneficiaries
	 * 
	 * It throws UserNotFoundException .This exception occurs when user does not
	 * found while adding beneficiary It throws BeneficiaryAlreadyExistException.
	 * This exception occurs when beneficiary already exists while adding new one
	 */

	@Override
	public MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException,
			MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException {
		logger.info("addBeneficiary service method - adding beneficiries ");
		Beneficiary beneficiary = new Beneficiary();
		Optional<Customer> customer = customerRepository.findById(addBeneficiaryRequestDto.getCustomerId());
		Optional<Account> account = accountRepository.findById(addBeneficiaryRequestDto.getBeneficiaryAccountNumber());

		if (!customer.isPresent()) {
			logger.debug("addBeneficiary service method - UserNotFoundException occured ");
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}
		if (beneficiaryRepository.countByCustomer(customer.get()) > Constant.MAX_BENEFICIARY_COUNT) {
			logger.debug("addBeneficiary service method - MaximumBeneficiaryException occured");
			throw new MaximumBeneficiaryException(Constant.MAX_BENEFICIARY);
		}
		if (!(account.isPresent() && account.get().getAccountNumber().equals(addBeneficiaryRequestDto.getBeneficiaryAccountNumber()))) {
			logger.debug("addBeneficiary service method - AccountNotFoundException occured");
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		Optional<Beneficiary> beneficiary1 = beneficiaryRepository.findByBeneficiaryAccountAndCustomer(account.get(),
				customer.get());
		if (beneficiary1.isPresent()) {
			if (beneficiary1.get().getStatus().equals(Constant.IN_ACTIVE)) {
				beneficiary1.get().setBeneficiaryName(addBeneficiaryRequestDto.getBeneficiaryName());
				beneficiary1.get().setStatus(Constant.ACTIVE);
				beneficiaryRepository.save(beneficiary1.get());
				logger.info("addBeneficiary service method - beneficiary added ");
				MessageDto messageDto = new MessageDto();
				messageDto.setMessage(Constant.SUCCESS);
				messageDto.setStatusCode(HttpStatus.OK.value());
				return messageDto;
			}
			logger.debug("addBeneficiary service method - BeneficiaryAlreadyExistException occured ");
			throw new BeneficiaryAlreadyExistException(Constant.BENEFICIARY_ALREADY_EXIST);

		} else {
			beneficiary.setBeneficiaryAccount(account.get());
			beneficiary.setBeneficiaryName(addBeneficiaryRequestDto.getBeneficiaryName());
			beneficiary.setCustomer(customer.get());
			beneficiary.setStatus(Constant.ACTIVE);
			beneficiaryRepository.save(beneficiary);
			logger.info("addBeneficiary service method - beneficiary added");
			MessageDto messageDto = new MessageDto();
			messageDto.setMessage(Constant.SUCCESS);
			messageDto.setStatusCode(HttpStatus.OK.value());
			return messageDto;
		}
	}

	/**
	 * This API is used to delete the beneficiary by passing the beneficiaryId
	 * 
	 * By passing the beneficiaryId the unwanted beneficiary can be deleted
	 * 
	 * This returns the messageDto which includes message and statuscode It throws
	 * BeneficiaryNotFoundException This exception occurs when beneficiary does not
	 * found
	 */

	@Override
	public MessageDto deleteBeneficiary(Integer beneficiaryId) throws BeneficiaryNotFoundException {
		logger.info("deleteBeneficiary service method - deleting beneficirary ");
		MessageDto messageDto = new MessageDto();
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findByBeneficiaryId(beneficiaryId);
		if (!beneficiary.isPresent()) {
			logger.debug("deleteBeneficiary service method - BeneficiaryNotFoundException occured");
			throw new BeneficiaryNotFoundException(Constant.BENEFICIARY_NOT_FOUND);
		}
		beneficiary.get().setStatus(Constant.IN_ACTIVE);
		beneficiaryRepository.save(beneficiary.get());
		logger.info("deleteBeneficiary service method - beneficiary removed");
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(Constant.BENEFICIARY_DELETED);
		return messageDto;
	}

	/**
	 * This API is used to update the beneficiary details by passing the
	 * updateBeneficiaryRequestDto
	 * 
	 * updateBeneficiaryRequestDto includes beneficiaryName and beneficiaryId This
	 * returns the new edited beneficiary details
	 * 
	 * It throws BeneficiaryNotFoundException. This exception occurs when
	 * beneficiary does not found while editing the beneficiaryDetails
	 */

	@Override
	public MessageDto updateBeneficiary(UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto)
			throws BeneficiaryNotFoundException, AccountNotFoundException, IfscNotMatchedException {
		logger.info("updateBeneficiary service method - updating beneficiary ");
		Optional<Beneficiary> beneficiary = beneficiaryRepository
				.findById(updateBeneficiaryRequestDto.getBeneficiaryId());
		Optional<Account> account = accountRepository
				.findById(updateBeneficiaryRequestDto.getBeneficiaryAccountNumber());
		if (!beneficiary.isPresent()) {
			logger.debug("updateBeneficiary service method - BeneficiaryNotFoundException occured ");
			throw new BeneficiaryNotFoundException(Constant.BENEFICIARY_NOT_FOUND);
		} else if (!account.isPresent()) {
			logger.debug("updateBeneficiary service method - AccountNotFoundException occured");
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		} else if (!account.get().getBank().getIfscCode().equals(updateBeneficiaryRequestDto.getIfscCode())) {
			logger.debug("updateBeneficiary service method - IfscNotMatchedException occured");
			throw new IfscNotMatchedException(Constant.IFSC_NOT_FOUND);
		} else {
			beneficiary.get().setBeneficiaryAccount(account.get());
			beneficiary.get().setBeneficiaryName(updateBeneficiaryRequestDto.getBeneficiaryName());
			beneficiaryRepository.save(beneficiary.get());
			logger.info("updateBeneficiary service method - beneficiary updated");
			MessageDto messageDto = new MessageDto();
			messageDto.setMessage(Constant.SUCCESS);
			messageDto.setStatusCode(HttpStatus.OK.value());
			return messageDto;
		}
	}

}
