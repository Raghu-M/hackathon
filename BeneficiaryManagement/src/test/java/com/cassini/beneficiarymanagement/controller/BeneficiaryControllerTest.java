package com.cassini.beneficiarymanagement.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.IfscNotMatchedException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryControllerTest {

	@InjectMocks
	BeneficiaryController beneficiaryController;

	@Mock
	BeneficiaryService beneficiaryService;

	List<Beneficiary> beneficiaries = null;
	Beneficiary beneficiary = null;
	Customer customer = null;

	@Test
	public void getAllBeneficiaryTest() {

		customer = new Customer();
		customer.setCustomerId(1);
		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(11);
		beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		Mockito.when(beneficiaryService.getAllBeneficiary(1)).thenReturn(new ArrayList<BeneficiaryListDto>());
		assertNotNull(beneficiaryController.getAllBeneficiary(1));
	}

	@Test
	public void addBeneficieries() throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException,
			BeneficiaryAlreadyExistException {
		Mockito.when(beneficiaryService.addBeneficiary(Mockito.any())).thenReturn(new MessageDto());
		assertNotNull(beneficiaryController.addBeneficiary(new AddBeneficiaryRequestDto()));

	}
	
	@Test
	public void testDeleteBeneficiary() throws BeneficiaryNotFoundException {
	MessageDto messageDto=new MessageDto();
	Mockito.when(beneficiaryService.deleteBeneficiary(1)).thenReturn(messageDto);
	ResponseEntity<MessageDto> response= beneficiaryController.deleteBeneficiary(1);
	assertNotNull(response);
	}

	@Test
	public void updateBeneficiary() throws BeneficiaryNotFoundException, AccountNotFoundException, IfscNotMatchedException {
	UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
	updateBeneficiaryRequestDto.setBeneficiaryId(1);
	updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
	updateBeneficiaryRequestDto.setBeneficiaryAccountNumber(6546457567L);
	updateBeneficiaryRequestDto.setIfscCode("SBIN000987");
	MessageDto messageDto = new MessageDto();
	messageDto.setMessage(Constant.SUCCESS);
	messageDto.setStatusCode(200);
	Mockito.when(beneficiaryService.updateBeneficiary(updateBeneficiaryRequestDto)).thenReturn(messageDto);
	MessageDto response = beneficiaryController.updateBeneficiary(updateBeneficiaryRequestDto);
	assertNotNull(response);
	}
	

}
