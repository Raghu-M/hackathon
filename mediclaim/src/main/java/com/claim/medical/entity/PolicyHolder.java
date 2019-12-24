package com.claim.medical.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PolicyHolder {

	@Id
	private Long policyHolderId;
	private Long policyNumber;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double policyAmount;
	private String policyHolderName;

}
