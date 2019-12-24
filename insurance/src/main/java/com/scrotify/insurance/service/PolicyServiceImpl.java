package com.scrotify.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.insurance.dto.PolicyResponseDto;
import com.scrotify.insurance.dto.TrendingResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.repository.PolicyRepository;
import com.scrotify.insurance.repository.PurchasePolicyRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This service has a method has the implementations of the policies.
 * 
 * @Api getTrendingPolicies which is used to get the trending policies
 *      considering the last ten purchase.
 * 
 * @author Anisha R
 * @author Raghu M
 * 
 */

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

	/**
	 * This will inject all the implementations in the policyRepository.
	 */

	@Autowired
	PolicyRepository policyRepository;

	/**
	 * This will inject all the implementations in the purchasePolicyRepository.
	 */

	@Autowired
	PurchasePolicyRepository purchasePolicyRepository;

	/**
	 * This API is used to get the list of all policies.
	 *
	 * @return This returns the list of policies available which contains the
	 *         details of policies.
	 * 
	 * @author Anisha R
	 * 
	 */

	@Override
	public List<PolicyResponseDto> listAllPolicies() {
		log.info("getPoliciesList : getting the list of all policies");
		List<PolicyResponseDto> policyResponseDto = new ArrayList<>();
		List<Policy> listPolicy = policyRepository.findAll();
		listPolicy.stream().forEach(policy -> {
			PolicyResponseDto listPolicyDto = new PolicyResponseDto();
			listPolicyDto.setPolictyMaximumAge(policy.getPolictyMaximumAge());
			listPolicyDto.setPolicyMinimumTerm(policy.getPolicyMaximumTerm());
			listPolicyDto.setPolicyMaximumTerm(policy.getPolicyMaximumTerm());
			listPolicyDto.setPolicyMinimumAge(policy.getPolicyMinimumAge());
			listPolicyDto.setPolicyName(policy.getPolicyName());
			listPolicyDto.setPolicySoldCount(policy.getPolicySoldCount());
			listPolicyDto.setMinimumPolicyPrice(policy.getMinimumPolicyPrice());
			listPolicyDto.setMaximumMaturityAge(policy.getMaximumMaturityAge());
			listPolicyDto.setMinimumSumAssured(policy.getMinimumSumAssured());
			listPolicyDto.setSailentFeatures(policy.getSailentFeatures());
			listPolicyDto.setPolicyDescription(policy.getDescription());
			listPolicyDto.setPolicyId(policy.getPolicyId());
			policyResponseDto.add(listPolicyDto);
		});
		return policyResponseDto;

	}

	/**
	 * This API is used to get the trending policies considering the last ten
	 * purchase.
	 * 
	 * @return This returns the list of trending policies which contain policy name
	 *         and number of policies purchased.
	 * 
	 * @author Raghu M
	 */

	@Override
	public List<TrendingResponseDto> getTrendingPolicies() {

		log.info("getTrendingPolicies service : getting trending policies");
		List<Policy> policies = policyRepository.findAll();
		List<TrendingResponseDto> trendingResponseDtos = new ArrayList<>();

		policies.forEach(policy -> {
			TrendingResponseDto trendingResponseDto = new TrendingResponseDto();
			trendingResponseDto.setPolicyId(policy.getPolicyId());
			trendingResponseDto.setPolicyName(policy.getPolicyName());
			trendingResponseDto.setPolicySoldCount(0);
			trendingResponseDtos.add(trendingResponseDto);

		});
		List<PurchasePolicy> purchasePolicies = purchasePolicyRepository
				.findTop10ByStatusOrderByPurchasePolicyIdDesc("active");
		purchasePolicies.forEach(purchasePolicy -> {

			for (TrendingResponseDto trendingResponseDto : trendingResponseDtos) {
				if (trendingResponseDto.getPolicyId().equals(purchasePolicy.getPolicy().getPolicyId())) {
					trendingResponseDto.setPolicySoldCount(trendingResponseDto.getPolicySoldCount() + 1);
				}
			}

		});

		return trendingResponseDtos;
	}

}
