package com.scrotify.flexibank.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexibank.dto.CustomerRequestDto;
import com.scrotify.flexibank.dto.CustomerResponseDto;
import com.scrotify.flexibank.dto.LoginRequestDto;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.exception.EmailAlreadyExistException;
import com.scrotify.flexibank.exception.UserNameAlreadyExistException;
import com.scrotify.flexibank.exception.UserNotFoundException;
import com.scrotify.flexibank.service.CustomerService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Test
	public void authenticateCustomerTestPositive() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("priya");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		Mockito.when(customerService.authenticateCustomer(loginRequestDto)).thenReturn(customer);
		ResponseEntity<Customer> actual = customerController.authenticateCustomer(loginRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

	@Test
	public void testRegisterCustomerPositive() throws UserNameAlreadyExistException, EmailAlreadyExistException {

		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setName("Nivi");
		customerRequestDto.setEmailId("nivi@gmail.com");
		customerRequestDto.setMobileNo(9898767654L);
		customerRequestDto.setUserName("Nivi");
		customerRequestDto.setPassword("nivi");
		customerRequestDto.setDateOfBirth(LocalDate.now());
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setStatusCode(HttpStatus.OK.value());

		Mockito.when(customerService.registerCustomer(customerRequestDto)).thenReturn(customerResponseDto);
		int result = customerController.registerCustomer(customerRequestDto).getStatusCode();
		assertEquals(HttpStatus.OK.value(), result);
	}

	@Test
	public void testRegisterCustomerFailure() throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setName("Nivi");
		customerRequestDto.setEmailId("nivi@gmail.com");
		customerRequestDto.setMobileNo(9898767654L);
		customerRequestDto.setUserName("Nivi");
		customerRequestDto.setPassword("nivi");
		customerRequestDto.setDateOfBirth(LocalDate.now());
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		Mockito.when(customerService.registerCustomer(customerRequestDto)).thenReturn(customerResponseDto);
		int result = customerController.registerCustomer(customerRequestDto).getStatusCode();
		assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), result);
	}

}