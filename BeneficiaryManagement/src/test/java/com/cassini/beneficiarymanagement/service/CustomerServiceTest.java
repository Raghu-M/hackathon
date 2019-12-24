package com.cassini.beneficiarymanagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.repository.AccountRepository;
import com.cassini.beneficiarymanagement.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	AccountRepository accountRepository;

	@Test
	public void AuthenticateCustomerTestPositive() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		customer.setAddress("bng");
		customer.setCustomerName("reddy");
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmailId("yoha@gmail.com");
		customer.setPhoneNumber(945654645L);
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("sri");
		Mockito.when(customerRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword())).thenReturn(Optional.of(customer));
		Customer loginResponseDto1 = customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertNotNull(loginResponseDto1);
	}

	@Test(expected = UserNotFoundException.class)
	public void authenticateCustomerTestNegative() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		customer.setAddress("bng");
		customer.setCustomerName("reddy");
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmailId("yoha@gmail.com");
		customer.setPhoneNumber(945654645L);
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		Mockito.when(customerRepository.findByUserNameAndPassword("priya", "sri")).thenReturn(Optional.of(customer));
		Customer loginResponseDto1 = customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertNull(loginResponseDto1);
	}

}
