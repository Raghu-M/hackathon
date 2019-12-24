package com.claim.medical.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "claimId", initialValue = 987654, allocationSize = 1)
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claimId")
	private Long claimId;
	private LocalDate claimDate;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "policyHolderId")
	private PolicyHolder policyHolder;
	private Long policyNumber;
	private String diagnosis;
	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private String hospitalName;
	private String dischargeSummary;
	private String ailmentType;
	private Double doctorFee;
	private Double medicalFee;
	private String claimStatus;
	private String approverComments;

}
