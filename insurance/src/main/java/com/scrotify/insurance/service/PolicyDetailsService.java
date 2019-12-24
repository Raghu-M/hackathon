package com.scrotify.insurance.service;

import com.scrotify.insurance.dto.PolicyDetailsResponseDto;

public interface PolicyDetailsService {
	PolicyDetailsResponseDto getPolicyDetails(Integer policyId);

}
