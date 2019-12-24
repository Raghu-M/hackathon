package com.scrotify.insurance.utils;

public class ApiConstant {
    private ApiConstant(){}

    public static final String SUCCESS = "Policy purchased successfully";
    public static final String FAILED = "FAILED";
    public static final Integer SUCCESS_STATUS_CODE = 201;
    public static final Integer FAILURE_STATUS_CODE = 204;

    public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
    public static final String VALIDATION_FAILED = "VALIDATION FAILED";
    public static final String NO_ELEMENT_FOUND = "NO ELEMENT FOUND";
	public static final String NO_POLICY_FOUND = "Policy Id not found";
	public static final String NO_POLICY_HOLDER_FOUND = "Policy Holder Id is not found ";
}

