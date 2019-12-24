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
import com.scrotify.insurance.dto.PolicyResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.service.PolicyServiceImpl;
import com.scrotify.insurance.service.PolicyService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyControllerTest {

	@InjectMocks
	PolicyController policyController;

	@Mock
	PolicyServiceImpl policyServiceImpl;
	
	@Mock
	PolicyService policyService;
	
	@Mock
	PolicyResponseDto PolicyResponseDto;

	List<PolicyResponseDto> policyResponseDtos;

	@Before
	public void setUp() {
		Policy policy = new Policy();
		policy.setMaximumMaturityAge(12);
		policy.setMinimumPolicyPrice(3000D);
		policy.setMinimumSumAssured(500D);
		policy.setPolictyMaximumAge(30);
		policy.setPolicyId(1);

		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setMaximumMaturityAge(policy.getMaximumMaturityAge());

		policyResponseDtos = new ArrayList<>();
		policyResponseDtos.add(policyResponseDto);
	}

	@Test
	public void testListAllPolicies() {
		Mockito.when(policyServiceImpl.listAllPolicies()).thenReturn(policyResponseDtos);
		ResponseEntity<List<PolicyResponseDto>> response = policyController.getListOfAllPolicies();
		assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
	}

	@Test
	public void testGetTrendingPolicies() {

		Mockito.when(policyService.getTrendingPolicies()).thenReturn(new ArrayList<>());
		Integer expected = 200;
		Integer actual = policyController.getTrendingPolicies().getStatusCode().value();
		assertEquals(expected, actual);

	}

}
