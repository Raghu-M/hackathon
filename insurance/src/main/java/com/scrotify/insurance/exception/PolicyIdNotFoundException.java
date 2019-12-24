package com.scrotify.insurance.exception;

public class PolicyIdNotFoundException extends RuntimeException {

 
	private static final long serialVersionUID = 1L;

	
    public PolicyIdNotFoundException(String msg) {
        super(msg);
    } 

}