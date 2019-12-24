package com.cassini.beneficiarymanagement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Bank;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class BeneficiaryServiceTest {

	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	List<Beneficiary> beneficiaries = null;
	Beneficiary beneficiary = null;
	Customer customer = null;

	@Test
	public void getAllBeneficiaryTest() {
		
		Bank bank = new Bank();
		bank.setBankName("test");
		bank.setBranchName("test");
		bank.setIfscCode("test");
		Account account = new Account();
		account.setAccountNumber(876687L);
		account.setBank(bank);
		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAccount(account);
		beneficiary.setBeneficiaryName("test");
		beneficiary.setBeneficiaryId(11);
		beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		Mockito.when(beneficiaryRepository.findAllByCustomerAndStatusOrderByBeneficiaryNameAsc(Mockito.any(), Mockito.any()))
				.thenReturn(beneficiaries);
		assertNotNull(beneficiaryServiceImpl.getAllBeneficiary(1));

	}

	@Test(expected = UserNotFoundException.class)
	public void testAddBeneficiaryUserNull() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		beneficiaryServiceImpl.addBeneficiary(new AddBeneficiaryRequestDto());

	}

	@Test(expected = MaximumBeneficiaryException.class)
	public void testAddBeneficiaryMaxNull() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(new Customer()));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		Mockito.when(beneficiaryRepository.countByCustomer(Mockito.any())).thenReturn(11L);
		beneficiaryServiceImpl.addBeneficiary(new AddBeneficiaryRequestDto());

	}

	@Test(expected = AccountNotFoundException.class)
	public void testAddBeneficiaryAccountNull() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(new Customer()));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		Mockito.when(beneficiaryRepository.countByCustomer(Mockito.any())).thenReturn(1L);
		beneficiaryServiceImpl.addBeneficiary(new AddBeneficiaryRequestDto());

	}

	@Test(expected = BeneficiaryAlreadyExistException.class)
	public void testAddBeneficiaryExistNull() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setStatus(Constant.ACTIVE);
		
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(new Customer()));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Account()));
		Mockito.when(beneficiaryRepository.countByCustomer(Mockito.any())).thenReturn(1L);
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountAndCustomer(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.ofNullable(beneficiary));
		beneficiaryServiceImpl.addBeneficiary(new AddBeneficiaryRequestDto());

	}
	
	@Test
	public void testAddBeneficiaryInactive() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		
		AddBeneficiaryRequestDto addBeneficiaryRequestDto = new AddBeneficiaryRequestDto();
		addBeneficiaryRequestDto.setBeneficiaryName("test");
		
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setStatus(Constant.IN_ACTIVE);
		
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(new Customer()));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Account()));
		Mockito.when(beneficiaryRepository.countByCustomer(Mockito.any())).thenReturn(1L);
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountAndCustomer(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.ofNullable(beneficiary));
		assertNotNull(beneficiaryServiceImpl.addBeneficiary(addBeneficiaryRequestDto).getStatusCode());

	}

	@Test
	public void testAddBeneficiarySuccess() throws AccountNotFoundException, MaximumBeneficiaryException,
			UserNotFoundException, BeneficiaryAlreadyExistException {
		AddBeneficiaryRequestDto addBeneficiaryRequestDto = new AddBeneficiaryRequestDto();
		addBeneficiaryRequestDto.setBeneficiaryName("test");
		Account account = new Account();
		account.setAccountNumber(1L);
		Customer customer = new Customer();
		customer.setCustomerId(1);
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(account));
		Mockito.when(beneficiaryRepository.countByCustomer(Mockito.any())).thenReturn(1L);
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountAndCustomer(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.ofNullable(null));
		assertNotNull(beneficiaryServiceImpl.addBeneficiary(addBeneficiaryRequestDto).getStatusCode());

	}

	@Test
	public void testDeleteBeneficiary() throws BeneficiaryNotFoundException {
		Beneficiary beneficiary = new Beneficiary();
		MessageDto messageDto = new MessageDto();
		Mockito.when(beneficiaryRepository.findByBeneficiaryId(1)).thenReturn(Optional.of(beneficiary));
		messageDto.setMessage("success");
		messageDto.setStatusCode(200);
		MessageDto response = beneficiaryServiceImpl.deleteBeneficiary(1);
		assertNotNull(response);

	}
	
	@Test(expected = BeneficiaryNotFoundException.class)
	public void testupdateBeneficiaryBeneficiaryNull() throws AccountNotFoundException, BeneficiaryNotFoundException, IfscNotMatchedException {
		
		Mockito.when(beneficiaryRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		beneficiaryServiceImpl.updateBeneficiary(new UpdateBeneficiaryRequestDto());
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void testupdateBeneficiaryAccountNull() throws AccountNotFoundException, BeneficiaryNotFoundException, IfscNotMatchedException {
		Mockito.when(beneficiaryRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Beneficiary()));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		beneficiaryServiceImpl.updateBeneficiary(new UpdateBeneficiaryRequestDto());
	}
	
	@Test(expected = IfscNotMatchedException.class)
	public void testupdateBeneficiaryIfscNotMatched() throws AccountNotFoundException, BeneficiaryNotFoundException, IfscNotMatchedException {
		
		Bank bank = new Bank();
		bank.setIfscCode("test");
		Account account = new Account();
		account.setAccountNumber(6546457567L);
		account.setBank(bank);
		
		UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
		updateBeneficiaryRequestDto.setBeneficiaryId(1);
		updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
		updateBeneficiaryRequestDto.setBeneficiaryAccountNumber(6546457567L);
		updateBeneficiaryRequestDto.setIfscCode("SBIN000987");
		
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1);
		beneficiary.setBeneficiaryName("yoga");
		beneficiary.setBeneficiaryAccount(account);
		
		Mockito.when(beneficiaryRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(beneficiary));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(account));
		beneficiaryServiceImpl.updateBeneficiary(updateBeneficiaryRequestDto);
	}
	
	@Test
	public void testupdateBeneficiarySuccess() throws AccountNotFoundException, BeneficiaryNotFoundException, IfscNotMatchedException {
		
		Bank bank = new Bank();
		bank.setIfscCode("SBIN000987");
		Account account = new Account();
		account.setAccountNumber(6546457567L);
		account.setBank(bank);
		
		UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
		updateBeneficiaryRequestDto.setBeneficiaryId(1);
		updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
		updateBeneficiaryRequestDto.setBeneficiaryAccountNumber(6546457567L);
		updateBeneficiaryRequestDto.setIfscCode("SBIN000987");
		
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1);
		beneficiary.setBeneficiaryName("yoga");
		beneficiary.setBeneficiaryAccount(account);
		
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(200);
		
		Mockito.when(beneficiaryRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(beneficiary));
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(account));
		MessageDto response = beneficiaryServiceImpl.updateBeneficiary(updateBeneficiaryRequestDto);
		assertEquals(messageDto.getStatusCode(), response.getStatusCode());
	}

}
