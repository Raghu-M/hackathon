package com.claim.medical.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimRequestDto {
	private String name;
	private Long policyHolderId;
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

}
