package com.scrotify.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.exception.PolicyHolderIdNotFoundException;
import com.scrotify.insurance.repository.PolicyHolderRepository;
import com.scrotify.insurance.repository.PurchasePolicyRepository;
import com.scrotify.insurance.utils.ApiConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolicyHolderServiceImpl implements PolicyHolderService {

	/**
	 * This will inject all the implementations of the policyHolderRepository.
	 */

	@Autowired
	PolicyHolderRepository policyHolderRepository;

	/**
	 * This will inject all the implementations of the purchasePolicyRepository.
	 */

	@Autowired
	PurchasePolicyRepository purchasePolicyRepository;

	/**
	 * This API is used to get the details of the policy Holder.
	 * 
	 * @param policyHolderId
	 *
	 * @return This returns the list of policies of policy holder which contains the
	 *         details of policies.
	 * 
	 * @author Anisha R
	 * 
	 */
	@Override
	public List<Policy> viewMyPolicy(Integer policyHolderId) {
		log.info(
				"Entering into policy holder Controller: where policyHolderId is passed to check his own policy details ");
		List<PurchasePolicy> purchasePolicys = purchasePolicyRepository
				.findByPolicyHolderPolicyHolderId(policyHolderId);
		if (purchasePolicys.isEmpty()) {
			log.error("Here it throws Policy Holder Not Found Exception");
			throw new PolicyHolderIdNotFoundException(ApiConstant.NO_POLICY_HOLDER_FOUND);
		}
		List<Policy> policies = new ArrayList<>();
		purchasePolicys.stream().forEach(purchasePolicy -> 
			policies.add(purchasePolicy.getPolicy())
		);
		log.info("end of the policy");
		return policies;

	}
}
