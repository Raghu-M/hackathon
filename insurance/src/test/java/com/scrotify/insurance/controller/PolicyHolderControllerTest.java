package com.scrotify.insurance.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PolicyHolder;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.service.PolicyHolderService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyHolderControllerTest {

	@InjectMocks
	PolicyHolderController policyHolderController;

	@Mock
	PolicyHolderService policyHolderService;

	List<Policy> list;

	List<PurchasePolicy> purchase;

	PolicyHolder policyHolder;

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
		policyHolder.setPolicyHolderId(1);

		PurchasePolicy policies = new PurchasePolicy();
		policies.setPolicyHolder(policyHolder);
	}

	@Test
	public void testListMyPolicies() {
		Mockito.when(policyHolderService.viewMyPolicy(Mockito.any())).thenReturn(list);
		ResponseEntity<List<Policy>> response = policyHolderController.getListOfMyPolicies(1);
		assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
	}

}
