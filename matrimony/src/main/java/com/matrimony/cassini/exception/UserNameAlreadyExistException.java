package com.matrimony.cassini.exception;

public class UserNameAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNameAlreadyExistException(String message) {
		super(message);
	}

}
