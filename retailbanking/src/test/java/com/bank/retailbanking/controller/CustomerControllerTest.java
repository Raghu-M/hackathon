package com.bank.retailbanking.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.CustomerRequestDto;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.dto.LoginResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.EmailAlreadyExistException;
import com.bank.retailbanking.exception.UserNameAlreadyExistException;
import com.bank.retailbanking.service.CustomerService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	@Test
	public void authenticateCustomerTestPositive() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("priya");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		LoginResponseDto loginResponseDto =new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_SUCCESS);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		loginResponseDto.setStatusCode(Constant.ACCEPTED);
		Mockito.when(customerService.authenticateCustomer(loginRequestDto)).thenReturn(loginResponseDto);
		LoginResponseDto actual =customerController.authenticateCustomer(loginRequestDto);
		assertEquals("Login Successful", actual.getMessage());
	}
	
	@Test
	public void testRegisterCustomerPositive() throws UserNameAlreadyExistException, EmailAlreadyExistException{

		CustomerRequestDto customerRequestDto=new CustomerRequestDto();
		customerRequestDto.setFirstName("Nivi");
		customerRequestDto.setLastName("R");
		customerRequestDto.setEmailId("nivi@gmail.com");
		customerRequestDto.setMobileNo(9898767654L);
		customerRequestDto.setUserName("Nivi");
		customerRequestDto.setPassword("nivi");
		customerRequestDto.setDateOfBirth(LocalDate.now());
		CustomerResponseDto customerResponseDto=new CustomerResponseDto();
		customerResponseDto.setStatusCode(HttpStatus.OK.value());

	     Mockito.when(customerService.registerCustomer(customerRequestDto)).thenReturn(customerResponseDto);
	     int result=customerController.registerCustomer(customerRequestDto).getStatusCode();
	     assertEquals(HttpStatus.OK.value(), result);
	}
	@Test
	public void testRegisterCustomerFailure() throws UserNameAlreadyExistException, EmailAlreadyExistException{

		CustomerRequestDto customerRequestDto=new CustomerRequestDto();
		customerRequestDto.setFirstName("Nivi");
		customerRequestDto.setLastName("R");
		customerRequestDto.setEmailId("nivi@gmail.com");
		customerRequestDto.setMobileNo(9898767654L);
		customerRequestDto.setUserName("Nivi");
		customerRequestDto.setPassword("nivi");
		customerRequestDto.setDateOfBirth(LocalDate.now());
		CustomerResponseDto customerResponseDto=new CustomerResponseDto();
		customerResponseDto.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());

	     Mockito.when(customerService.registerCustomer(customerRequestDto)).thenReturn(customerResponseDto);
	     int result=customerController.registerCustomer(customerRequestDto).getStatusCode();
	     assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), result);
	}

}