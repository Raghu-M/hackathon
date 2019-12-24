package com.cassini.beneficiarymanagement.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.repository.BankRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankServiceTest {

	@InjectMocks
	BankServiceImpl bankServiceImpl;

	@Mock
	BankRepository bankRepository;

	@Test
	public void findBankByIfscCode() throws BankNotFound {
		Bank bank = new Bank();
		bank.setBankId(1);
		bank.setBankName("IOB");
		bank.setBranchName("Trichy");
		bank.setIfscCode("SBI09787");
		Mockito.when(bankRepository.findByIfscCode("SBI09787")).thenReturn(Optional.of(bank));
		Bank actual = bankServiceImpl.getBankDetails("SBI09787");
		assertNotNull(actual);
	}
	
	
	@Test(expected = BankNotFound.class)
	public void findBankByIfscCodeNew() throws BankNotFound {
		Bank bank = new Bank();
		bank.setBankId(1);
		bank.setBankName("IOB");
		bank.setBranchName("Trichy");
		bank.setIfscCode("SBI099757");
		Mockito.when(bankRepository.findByIfscCode("SBI09787")).thenReturn(Optional.of(bank));
		Bank actual = bankServiceImpl.getBankDetails("SBI09787566");
		assertNotNull(actual);
	}

}
