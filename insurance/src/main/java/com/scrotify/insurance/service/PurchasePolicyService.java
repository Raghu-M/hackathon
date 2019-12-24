package com.scrotify.insurance.service;

import com.scrotify.insurance.dto.PurchasePolicyRequestDto;
import com.scrotify.insurance.dto.PurchasePolicyResponseDto;

public interface PurchasePolicyService {
	PurchasePolicyResponseDto buyPolicy(PurchasePolicyRequestDto purchasePolicyRequestDto);
}
