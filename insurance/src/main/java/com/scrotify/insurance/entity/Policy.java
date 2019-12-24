package com.scrotify.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Policy {

	@Id
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
	@Column(length = 4194304)
	private byte[] sailentFeatures;
	@Column(length = 4194304)
	private byte[] termsAndConditions;
	@Column(length = 4194304)
	private byte[] description;

}
