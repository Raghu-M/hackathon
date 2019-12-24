package com.cassini.beneficiarymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeneficiaryListDto {

	private String beneficiaryName;
	private String bankName;
	private String branchName;
	private Long accountNumber;
	private Integer beneficiaryId;
	private String ifscCode;

}
