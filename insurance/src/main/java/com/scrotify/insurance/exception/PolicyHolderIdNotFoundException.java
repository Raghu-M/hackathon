package com.scrotify.insurance.exception;

public class PolicyHolderIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PolicyHolderIdNotFoundException(String msg) {
		super(msg);
	}
}