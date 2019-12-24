package com.bank.retailbanking.service;

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
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.dto.LoginResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.EmailAlreadyExistException;
import com.bank.retailbanking.exception.UserNameAlreadyExistException;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	
	@Test
	public void AuthenticateCustomerTestPositive() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("sri");
		LoginResponseDto loginResponseDto =new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_SUCCESS);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		Mockito.when(customerRepository.findByUserName("priya")).thenReturn(customer);
		LoginResponseDto loginResponseDto1=customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertEquals("Login Successful", loginResponseDto1.getMessage());
	}
	
	
	@Test
	public void authenticateCustomerTestNegative() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		LoginResponseDto loginResponseDto =new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_FAILURE);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		Mockito.when(customerRepository.findByUserName("priya")).thenReturn(customer);
		LoginResponseDto loginResponseDto1=customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertEquals("Authentication Failed", loginResponseDto1.getMessage());
	}
	
	@Test
	public void authenticateCustomerTestNegativeForCustomerNotFound() {
		Customer customer=null;
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		LoginResponseDto loginResponseDto =new LoginResponseDto();
		loginResponseDto.setMessage(Constant.CUSTOMER_NOT_FOUND);
		Mockito.when(customerRepository.findByUserName("priya")).thenReturn(customer);
		LoginResponseDto loginResponseDto1=customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertEquals("Authentication Failed", loginResponseDto1.getMessage());
	}
	
	@Test(expected = UserNameAlreadyExistException.class)
	public void testRegisterCustomerUserNameFailure() throws UserNameAlreadyExistException, EmailAlreadyExistException {
		
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setDateOfBirth(LocalDate.now());
		
		Integer expected = HttpStatus.NOT_ACCEPTABLE.value();
		Integer actual = customerServiceImpl.registerCustomer(customerRequestDto).getStatusCode();
		assertEquals(expected, actual);
		
		customerRequestDto.setDateOfBirth(LocalDate.parse("1995-12-12"));
		customerRequestDto.setUserName("test");
		Mockito.when(customerRepository.findByUserName("test")).thenReturn(new Customer());
		customerServiceImpl.registerCustomer(customerRequestDto);
		
		
		
		
	}
	
	@Test(expected = EmailAlreadyExistException.class)
	public void testRegisterCustomerEmailFailure() throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setDateOfBirth(LocalDate.now());
		customerRequestDto.setUserName("test");
		customerRequestDto.setEmailId("test@emil.com");
		customerRequestDto.setDateOfBirth(LocalDate.parse("1995-12-12"));
		Mockito.when(customerRepository.findByUserName("hello")).thenReturn(new Customer());
		Mockito.when(customerRepository.findByEmailId("test@emil.com")).thenReturn(new Customer());
		customerServiceImpl.registerCustomer(customerRequestDto);
		
	}
	
	
	@Test
	public void testRegisterCustomerSuccess() throws UserNameAlreadyExistException, EmailAlreadyExistException {
		
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setDateOfBirth(LocalDate.parse("1995-12-12"));
		customerRequestDto.setUserName("test");
		customerRequestDto.setEmailId("test@emil.com");
		
		Integer expected = HttpStatus.OK.value();
		
		Mockito.when(customerRepository.findByUserName("hello")).thenReturn(new Customer());
		Mockito.when(customerRepository.findByEmailId("test@emill.com")).thenReturn(new Customer());
		Mockito.when(customerRepository.save(new Customer())).thenReturn(new Customer());
		Mockito.when(accountRepository.save(new Account())).thenReturn(new Account());
		Integer actual = customerServiceImpl.registerCustomer(customerRequestDto).getStatusCode();
		assertEquals(expected, actual);
		
	}

}
