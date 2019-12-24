package com.scrotify.insurance.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author anisha.R
 *
 */
@Getter
@Setter
public class PolicyResponseDto {

	private Integer policyId;
	private String policyName;
	private Integer policyMinimumAge;
	private Integer polictyMaximumAge;
	private Integer policyMinimumTerm;
	private Integer policyMaximumTerm;
	private Double minimumPolicyPrice;
	private Integer policySoldCount;
	private Integer maximumMaturityAge;
	private Double minimumSumAssured;
	private byte[] sailentFeatures;
	private byte[] termsAndConditions;
	private byte[] policyDescription;

}
