package com.scrotify.insurance.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class PurchasePolicyRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer policyId;
	private String policyHolderName;
	private String gender;
	private Long mobileNumber;
	private String email;
	private String nomineeName;
	private String relationShipWithNominee;

}
