package com.claim.medical.exception;

public class PolicyExpiredException extends Exception {

	private static final long serialVersionUID = 1L;

	public PolicyExpiredException(String message) {
		super(message);
	}

}
