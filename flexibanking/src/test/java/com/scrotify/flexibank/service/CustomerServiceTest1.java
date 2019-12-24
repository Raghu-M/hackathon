package com.scrotify.flexibank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.CustomerRequestDto;
import com.scrotify.flexibank.dto.LoginRequestDto;
import com.scrotify.flexibank.dto.LoginResponseDto;
import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;
import com.scrotify.flexibank.entity.Customer;
import com.scrotify.flexibank.exception.EmailAlreadyExistException;
import com.scrotify.flexibank.exception.UserNameAlreadyExistException;
import com.scrotify.flexibank.exception.UserNotFoundException;
import com.scrotify.flexibank.repository.AccountRepository;
import com.scrotify.flexibank.repository.CreditCardRepository;
import com.scrotify.flexibank.repository.CustomerRepository;
import com.scrotify.flexibank.util.EmailService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest1 {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	AccountRepository accountRepository;
	
	@Mock
	CreditCardRepository creditCardRepository;
	
	@Mock
	EmailService emailService;

	@Test
	public void AuthenticateCustomerTestPositive() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("sri");
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_SUCCESS);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		Mockito.when(customerRepository.findByUserNameAndPassword("priya", "sri")).thenReturn(Optional.of(customer));
		Customer loginResponseDto1 = customerServiceImpl.authenticateCustomer(loginRequestDto);
		assertNotNull(loginResponseDto1);
	}

	@Test(expected = UserNotFoundException.class)
	public void authenticateCustomerTestNegative() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("sri");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_FAILURE);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		Mockito.when(customerRepository.findByUserNameAndPassword("priya", "sri")).thenReturn(Optional.of(customer));
		customerServiceImpl.authenticateCustomer(loginRequestDto);
	}

	@Test
	public void testRegisterCustomerLessAge() throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("test");
		customerRequestDto.setDateOfBirth(LocalDate.parse("2019-11-11"));
		customerRequestDto.setEmailId("test");
		customerRequestDto.setMobileNo(385767L);
		customerRequestDto.setName("test");
		customerRequestDto.setPassword("test");
		customerRequestDto.setSalary(87456347.0);
		customerRequestDto.setUserName("test");

		String expected = Constant.LESSER_AGE;
		String actual = customerServiceImpl.registerCustomer(customerRequestDto).getMessage();
		assertEquals(expected, actual);
	}

	@Test(expected = UserNameAlreadyExistException.class)
	public void testRegisterCustomerUserAlredyPresent()
			throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("test");
		customerRequestDto.setDateOfBirth(LocalDate.parse("1919-11-11"));
		customerRequestDto.setEmailId("test");
		customerRequestDto.setMobileNo(385767L);
		customerRequestDto.setName("test");
		customerRequestDto.setPassword("test");
		customerRequestDto.setSalary(87456347.0);
		customerRequestDto.setUserName("test");

		Mockito.when(customerRepository.findByUserName(customerRequestDto.getUserName()))
				.thenReturn(Optional.of(new Customer()));
		customerServiceImpl.registerCustomer(customerRequestDto);
	}

	@Test(expected = EmailAlreadyExistException.class)
	public void testRegisterCustomerEmailAlredyPresent()
			throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("test");
		customerRequestDto.setDateOfBirth(LocalDate.parse("1919-11-11"));
		customerRequestDto.setEmailId("test");
		customerRequestDto.setMobileNo(385767L);
		customerRequestDto.setName("test");
		customerRequestDto.setPassword("test");
		customerRequestDto.setSalary(87456347.0);
		customerRequestDto.setUserName("test");

		Mockito.when(customerRepository.findByUserName(customerRequestDto.getUserName()))
				.thenReturn(Optional.ofNullable(null));
		Mockito.when(customerRepository.findByEmailId(customerRequestDto.getEmailId())).thenReturn(Optional.of(new Customer()));
		customerServiceImpl.registerCustomer(customerRequestDto);
	}
	
	@Test
	public void testRegisterCustomerSuccess()
			throws UserNameAlreadyExistException, EmailAlreadyExistException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("test");
		customerRequestDto.setDateOfBirth(LocalDate.parse("1919-11-11"));
		customerRequestDto.setEmailId("test");
		customerRequestDto.setMobileNo(385767L);
		customerRequestDto.setName("test");
		customerRequestDto.setPassword("test");
		customerRequestDto.setSalary(87456347.0);
		customerRequestDto.setUserName("test");

		Mockito.when(customerRepository.findByUserName(customerRequestDto.getUserName()))
				.thenReturn(Optional.ofNullable(null));
		Mockito.when(customerRepository.findByEmailId(customerRequestDto.getEmailId())).thenReturn(Optional.ofNullable(null));
		Mockito.when(emailService.sendEmail(Mockito.any())).thenReturn("test");
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(new Customer());
		Mockito.when(creditCardRepository.save(Mockito.any())).thenReturn(new CreditCard());
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(new Account());
		String expected = Constant.SUCCESS;
		String actual = customerServiceImpl.registerCustomer(customerRequestDto).getMessage();
		assertEquals(expected, actual);
	}

}
