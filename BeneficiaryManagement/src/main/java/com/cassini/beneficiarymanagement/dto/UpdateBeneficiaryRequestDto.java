package com.cassini.beneficiarymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateBeneficiaryRequestDto {

	private String beneficiaryName;
	private Integer beneficiaryId;
	private Long beneficiaryAccountNumber;
	private String ifscCode;

}
