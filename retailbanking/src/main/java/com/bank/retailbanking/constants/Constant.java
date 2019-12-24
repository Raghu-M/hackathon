package com.bank.retailbanking.constants;

public class Constant {
	
	private Constant() {
		
	}

	/**
	 * Status messages.
	 */
	public static final String TRANSACTION_SUCCESS = "fund transfer successfull";
	public static final String NO_PAYER_ACCOUNT = "payer account is not available";
	public static final String NO_PAYEE_ACCOUNT = "payee account is not available";
	public static final String DEBIT_TRANSACTION = "debit";
	public static final String CREDIT_TRANSACTION = "credit";
	public static final String TRANSACTION_FAILURE = "no sufficient balance";
	public static final String LOGIN_SUCCESS = "Login Successful";
	public static final String LOGIN_FAILURE = "Authentication Failed";
	public static final String CUSTOMER_NOT_FOUND = "Customer Not Found";
	public static final String ACCOUNT_NOT_FOUND = "Account Not Found";
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String USER_NAME_ALREADY_EXIST = "UserName already exist";
	public static final String LESSER_AGE = "Age is less than 18years";
	public static final String EMAIL_ALREADY_EXISTS = "EmailId Already Exists";
	public static final String ACCOUNT_STATUS_ACTIVE = "Active";
	public static final String ACCOUNT_TYPE_SAVINGS = "savings";
	/**
	 * Status codes.
	 */
	public static final Integer NOT_ACCEPTABLE = 406;
	public static final Integer USER_NOT_ACCEPTABLE = 407;
	public static final Integer EMAIL_NOT_ACCEPTABLE = 408;
	public static final Integer ACCEPTED = 201;
	public static final Integer INVALID_AMOUNT = 410;
	public static final Integer LOGIN_FAILURE_CODE = 406;
	public static final Integer ACCOUNT_NOT_FOUND_CODE = 404;
	public static final Double MINIMUM_BALANCE = 500.00;

}
