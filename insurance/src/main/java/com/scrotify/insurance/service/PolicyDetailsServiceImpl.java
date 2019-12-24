package com.scrotify.insurance.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.insurance.dto.PolicyDetailsResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.exception.CommonException;
import com.scrotify.insurance.repository.PolicyRepository;
import com.scrotify.insurance.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-20 This class is used to get the details of the policy based
 *        on policyId.
 *
 */
@Service
public class PolicyDetailsServiceImpl implements PolicyDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(PolicyDetailsServiceImpl.class);
	@Autowired
	PolicyRepository policyRepository;

	/**
	 * This method is used to get the details of the policy based on policyId.
	 * 
	 * @param Integer policyId is the input parameter
	 * @return PolicyDetailsResponseDto which contains
	 *         policyId,policyName,minimumPolicyPrice,policySoldCount,sailentFeatures,policyDescription,termsAndConditions
	 */
	@Override
	public PolicyDetailsResponseDto getPolicyDetails(Integer policyId) {
		logger.info("Inside getPolicyDetails");
		PolicyDetailsResponseDto policyDetailsResponseDto = new PolicyDetailsResponseDto();
		Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
		if (!optionalPolicy.isPresent()) {
			logger.error("checking policy is exists or not");
			throw new CommonException(StringConstant.POLICY_NOT_FOUND);
		}
		Policy policies=optionalPolicy.get();
		BeanUtils.copyProperties(policies, policyDetailsResponseDto);
		return policyDetailsResponseDto;

	}

}
