package com.scrotify.insurance.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.insurance.dto.PurchasePolicyRequestDto;
import com.scrotify.insurance.dto.PurchasePolicyResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PolicyHolder;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.exception.CommonException;
import com.scrotify.insurance.repository.PolicyHolderRepository;
import com.scrotify.insurance.repository.PolicyRepository;
import com.scrotify.insurance.repository.PurchasePolicyRepository;
import com.scrotify.insurance.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-20 This class is used for to buy a policy.
 */
@Service
public class PurchasePolicyServiceImpl implements PurchasePolicyService {
	private static final Logger logger = LoggerFactory.getLogger(PurchasePolicyServiceImpl.class);
	@Autowired
	PurchasePolicyRepository purchasePolicyRepository;
	@Autowired
	PolicyRepository policyRepository;
	@Autowired
	PolicyHolderRepository policyHolderRepository;

	/**
	 * This method is used for to buy a policy.
	 * 
	 * @param purchasePolicyRequestDto it is the request object which contains
	 *                                 policyHolderName,gender,mobileNumber,email,nomineeName
	 *                                 and relationShipWithNominne.
	 * @return it returns PurchasePolicyResponseDto object which contains message
	 *         and statusCode.
	 */
	@Override
	public PurchasePolicyResponseDto buyPolicy(PurchasePolicyRequestDto purchasePolicyRequestDto) {
		logger.info("Purchase policy Service impl");
		PurchasePolicy purchasePolicy = new PurchasePolicy();
		PurchasePolicyResponseDto purchasePolicyResponseDto = new PurchasePolicyResponseDto();
		Optional<Policy> policies = policyRepository.findById(purchasePolicyRequestDto.getPolicyId());
		if (!policies.isPresent())
			throw new CommonException(StringConstant.POLICY_NOT_FOUND);
		if (!validatePolicyHolderName(purchasePolicyRequestDto.getPolicyHolderName())) {
			logger.error("checking policy name");
		}

		PolicyHolder policyHolder = new PolicyHolder();
		policyHolder.setPolicyHolderName(purchasePolicyRequestDto.getPolicyHolderName());
		policyHolder.setGender(purchasePolicyRequestDto.getGender());
		policyHolder.setMobileNumber(purchasePolicyRequestDto.getMobileNumber());
		policyHolder.setNomineeName(purchasePolicyRequestDto.getNomineeName());
		policyHolder.setRelationShipWithNominee(purchasePolicyRequestDto.getRelationShipWithNominee());
		PolicyHolder policyHolderDbs = policyHolderRepository.save(policyHolder);

		purchasePolicy.setPolicyHolder(policyHolderDbs);
		purchasePolicy.setStatus(StringConstant.ACTIVE_STATUS);
		purchasePolicy.setPurchaseDate(LocalDate.now());
		Policy policy1 = new Policy();
		policy1.setPolicyId(purchasePolicyRequestDto.getPolicyId());
		purchasePolicy.setPolicy(policy1);
		purchasePolicyRepository.save(purchasePolicy);
		Optional<Policy> policy = policyRepository.findById(purchasePolicyRequestDto.getPolicyId());
		if (policy.get().getPolicySoldCount() == null) {
			policy.get().setPolicySoldCount(1);
		} else {
			policy.get().setPolicySoldCount(policy.get().getPolicySoldCount() + 1);
		}
		
		policyRepository.save(policy.get());
		return purchasePolicyResponseDto;

	}

	private boolean validatePolicyHolderName(String policyHolderName) {
		String name = ("^[a-zA-Z]*$");
		return policyHolderName.matches(name);
	}
}
