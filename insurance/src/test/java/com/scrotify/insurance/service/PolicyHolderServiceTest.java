package com.scrotify.insurance.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PolicyHolder;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.exception.PolicyHolderIdNotFoundException;
import com.scrotify.insurance.repository.PolicyHolderRepository;
import com.scrotify.insurance.repository.PurchasePolicyRepository;
import com.scrotify.insurance.utils.ApiConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyHolderServiceTest {

	@InjectMocks
	PolicyHolderServiceImpl policyHolderServiceImpl;

	@Mock
	PolicyHolderRepository policyHolderRepository;

	@Mock
	PurchasePolicyRepository purchasePolicyRepository;

	List<Policy> list;

	List<PurchasePolicy> purchase;

	PolicyHolder policyHolder;
	
	int one = 1;

	@Before
	public void setUp() {
		Policy policy = new Policy();
		policy.setMaximumMaturityAge(12);
		policy.setMinimumPolicyPrice(3000D);
		policy.setMinimumSumAssured(500D);
		policy.setPolictyMaximumAge(30);
		policy.setPolicyId(1);

		list = new ArrayList<>();
		list.add(policy);

		policyHolder = new PolicyHolder();
		policyHolder.setPolicyHolderId(one);

		PurchasePolicy policie = new PurchasePolicy();
		policie.setPolicyHolder(policyHolder);
		policie.setPurchaseDate(LocalDate.parse("1999-12-10"));

		purchase = new ArrayList<>();
		purchase.add(policie);
		
	}

	@Test
	public void testListMyPoliciesPositive() {
		Mockito.when(purchasePolicyRepository.findByPolicyHolderPolicyHolderId(Mockito.any())).thenReturn(purchase);
		List<Policy> response = policyHolderServiceImpl.viewMyPolicy(1);
		assertEquals(response.size(), one);
	}

	@Test(expected = PolicyHolderIdNotFoundException.class)
	public void testListMyPoliciesNegative() {
		Mockito.when(purchasePolicyRepository.findByPolicyHolderPolicyHolderId(2)).thenReturn(purchase);
		List<Policy> response = policyHolderServiceImpl.viewMyPolicy(one);
		assertEquals(ApiConstant.NO_POLICY_HOLDER_FOUND, response);
	}

}
