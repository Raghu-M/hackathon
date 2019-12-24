package com.claim.medical.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveClaimRequestDto {

	private String userName;
	private Integer approveStatus;
	private Long claimId;
	private String approverComment;

}
