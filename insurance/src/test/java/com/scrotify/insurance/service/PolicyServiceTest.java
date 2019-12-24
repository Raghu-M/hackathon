package com.scrotify.insurance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.scrotify.insurance.dto.PolicyResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.entity.PurchasePolicy;
import com.scrotify.insurance.repository.PurchasePolicyRepository;
import com.scrotify.insurance.repository.PolicyRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyServiceTest {

	@InjectMocks
	PolicyServiceImpl policyServiceImpl;

	@Mock
	PolicyRepository policyRepository;

	@Mock
	PurchasePolicyRepository purchasePolicyRepository;

	Policy policy;

	PolicyResponseDto PolicyResponseDto;

	List<PolicyResponseDto> policyResponseDtos;

	List<Policy> pol;
	
	int one = 1;

	@Before
	public void setUp() {
		Policy policy = new Policy();
		policy.setMaximumMaturityAge(12);
		policy.setMinimumPolicyPrice(3000D);
		policy.setMinimumSumAssured(500D);
		policy.setPolictyMaximumAge(30);
		policy.setPolicyId(one);

		pol = new ArrayList<>();
		pol.add(policy);

		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setMaximumMaturityAge(policy.getMaximumMaturityAge());

		policyResponseDtos = new ArrayList<>();
		policyResponseDtos.add(policyResponseDto);
	}

	@Test
	public void testListAllPolicies() {
		Mockito.when(policyRepository.findAll()).thenReturn(pol);
		List<PolicyResponseDto> response = policyServiceImpl.listAllPolicies();
		assertEquals(response.size(), one);

	}

	@Test
	public void testGetTrendingPoliciesSuccess() {

		Policy policy1 = new Policy();
		policy1.setPolicyId(one);
		policy1.setPolicyName("test");

		Policy policy2 = new Policy();
		policy2.setPolicyId(2);
		policy2.setPolicyName("test");
		List<Policy> policies = new ArrayList<Policy>();
		policies.add(policy1);
		policies.add(policy2);

		List<PurchasePolicy> purchasePolicies = new ArrayList<PurchasePolicy>();
		PurchasePolicy purchasePolicy1 = new PurchasePolicy();
		purchasePolicy1.setPolicy(policy1);
		PurchasePolicy purchasePolicy2 = new PurchasePolicy();
		purchasePolicy2.setPolicy(policy2);
		purchasePolicies.add(purchasePolicy1);
		purchasePolicies.add(purchasePolicy2);
		Mockito.when(policyRepository.findAll()).thenReturn(policies);
		Mockito.when(purchasePolicyRepository.findTop10ByStatusOrderByPurchasePolicyIdDesc(Mockito.any()))
				.thenReturn(new ArrayList<>());
		assertNotNull(policyServiceImpl.getTrendingPolicies());

	}

}
