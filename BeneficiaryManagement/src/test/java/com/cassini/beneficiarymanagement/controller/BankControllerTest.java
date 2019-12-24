package com.cassini.beneficiarymanagement.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.service.BankService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankControllerTest {

	@InjectMocks
	BankController bankController;

	@Mock
	BankService bankService;

	@Test
	public void findBankByIfscCode() throws BankNotFound {
		Bank bank = new Bank();
		bank.setBankId(1);
		bank.setBankName("IOB");
		bank.setBranchName("Trichy");
		bank.setIfscCode("SBI09787");
		Mockito.when(bankService.getBankDetails("SBI09787")).thenReturn(bank);
		ResponseEntity<Bank> actual = bankController.getBankDetails("SBI09787");
		assertNotNull(actual);
	}

}
