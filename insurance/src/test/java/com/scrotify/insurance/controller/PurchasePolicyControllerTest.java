package com.scrotify.insurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrotify.insurance.dto.PurchasePolicyRequestDto;
import com.scrotify.insurance.dto.PurchasePolicyResponseDto;
import com.scrotify.insurance.entity.Policy;
import com.scrotify.insurance.service.PurchasePolicyServiceImpl;
import com.scrotify.insurance.utils.ApiConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class PurchasePolicyControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(PurchasePolicyControllerTest.class);
	@InjectMocks
	PurchasePolicyController purchasePolicyController;
	@Mock
	PurchasePolicyServiceImpl purchasePolicyService;
	PurchasePolicyResponseDto purchasePolicyResponseDto = new PurchasePolicyResponseDto();
	PurchasePolicyRequestDto purchasePolicyRequestDto = new PurchasePolicyRequestDto();
	Policy policy = new Policy();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		purchasePolicyResponseDto.setMessage(ApiConstant.SUCCESS);
		purchasePolicyResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
		policy.setPolicyId(1);

	}

	@Test
	public void testBuyPolicy() {
		logger.debug("Inside buyPolicyTest method");
		when(purchasePolicyService.buyPolicy(purchasePolicyRequestDto)).thenReturn(purchasePolicyResponseDto);
		ResponseEntity<PurchasePolicyResponseDto> purchasePolicyResponseDto = purchasePolicyController.buyPolicy(1,
				purchasePolicyRequestDto);
		assertEquals(ApiConstant.SUCCESS, purchasePolicyResponseDto.getBody().getMessage());

	}
}