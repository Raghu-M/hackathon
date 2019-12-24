package com.cassini.beneficiarymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBeneficiaryRequestDto {

	private Integer customerId;
	private Long beneficiaryAccountNumber;
	private String ifscCode;
	private String beneficiaryName;

}
