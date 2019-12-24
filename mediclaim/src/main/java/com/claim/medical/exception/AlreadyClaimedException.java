package com.claim.medical.exception;

public class AlreadyClaimedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AlreadyClaimedException(String message) {
		super(message);
	}

}
