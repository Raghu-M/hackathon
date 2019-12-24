package com.cassini.beneficiarymanagement.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.IfscNotMatchedException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

@Service
public interface BeneficiaryService {

	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException,
			MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException;

	List<BeneficiaryListDto> getAllBeneficiary(Integer customerId);

	MessageDto deleteBeneficiary(Integer beneficiaryId) throws BeneficiaryNotFoundException;

	MessageDto updateBeneficiary(UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto)
			throws BeneficiaryNotFoundException, AccountNotFoundException, IfscNotMatchedException;

}
