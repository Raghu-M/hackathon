package com.scrotify.insurance.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PolicyDetailsResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer policyId;
	private String policyName;
	private Double minimumPolicyPrice;
	private Integer policySoldCount;
	private Byte[] sailentFeatures;
	private Byte[] policyDescription;
	private Byte[] termsAndConditions;
	private String message;
	private Integer statusCode;

}
