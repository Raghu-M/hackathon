package com.claim.medical.exception;

public class PolicyNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PolicyNotFoundException(String message) {
		super(message);
	}
}
