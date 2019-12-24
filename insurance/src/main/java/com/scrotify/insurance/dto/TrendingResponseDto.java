package com.scrotify.insurance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrendingResponseDto {

	private Integer policyId;
	private String policyName;
	private Integer policySoldCount;

}
