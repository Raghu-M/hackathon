package com.claim.medical.constants;

public class Constant {

	private Constant() {

	}

	public static final String USER_NOT_FOUND = "User Not Found";

	public static final String POLICY_HOLDER_NOT_FOUND = "Policyholder Not Found";
	public static final String POLICY_EXPIRED = "claim is Not covered under policy";
	public static final String CLAIM_RAISED = "Claim Raised Successfully";
	public static final String CLAIM_AMOUNT_INVALID = "Claim amount Invalid";
	public static final String CLAIM_PENDING_STAGE_1 = "pending at level 1";
	public static final String CLAIM_PENDING_STAGE_2 = "pending at level 2";
	public static final String POLICY_NOT_FOUND = "Policy Not Found";
	public static final String ALREADY_CLAIMED = "already claimed";
	public static final String CLAIM_APPROVE = "approved";
	public static final String CLAIM_DENY = "denied";
	public static final String CLAIM_REFER_BACK = "refered back";
	public static final String SUCCESS = "success";

	public static final Integer INVALID_CLAIM = 520;
	public static final Integer POLICY_EXPIRED_STATUS = 521;
	public static final Integer POLICY_HOLDER_NOT_FOUND_STATUS = 522;
	public static final Integer POLICY_NOT_FOUND_STATUS = 523;
	public static final Integer ALREADY_CLAIMED_STATUS = 524;
	public static final Integer ZERO_AMOUNT = 0;
	public static final Integer MAX_LEVEL_1APPROVE_AMOUNT = 10000;
	public static final Integer LEVEL_ONE_APPROVER = 1;
	public static final Integer LEVEL_TWO_APPROVER = 2;
	public static final Integer APPROVE = 1;
	public static final Integer DENY = 2;
	public static final Integer REFER_BACK = 3;

}
