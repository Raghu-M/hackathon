package com.claim.medical.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.claim.medical.entity.Claim;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.service.ClaimService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClaimControllerTest {
	private static final Claim Claim = null;
	@InjectMocks
	ClaimController claimController;
	@Mock
	ClaimService claimService;

	List<Claim> claimList = null;

	@Before
	public void setup() {
		claimList = new ArrayList<Claim>();
		Claim claim = new Claim();
		claim.setAdmittedDate(LocalDate.parse("2019-09-09"));
		claim.setAilmentType("ddd");
		claim.setApproverComments("ddd");
		claim.setClaimAmount(500.00);
		claim.setClaimDate(LocalDate.parse("2019-07-05"));
		claim.setClaimStatus("Approved");
		claim.setDiagnosis("cancer");
		claim.setDischargeDate(LocalDate.parse("2019-08-02"));
		claim.setDischargeSummary("done");
		claim.setDoctorFee(200.00);
		claim.setHospitalName("moorthy hospital");
		claim.setMedicalFee(300.00);
		claim.setName("zzz");
		claimList.add(claim);

	}

	@Test
	public void testClaimDetails() throws PolicyNotFoundException {
		Long claimId = 8888L;
		Mockito.when(claimService.viewClaimDetails(claimId)).thenReturn(Claim);
		ResponseEntity<Claim> response = claimController.claimDetails(claimId);
		Assert.assertNotNull(response);
	}

	@Test
	public void testGetClaims() throws UserNotFoundException {

		Mockito.when(claimService.getClaims(1)).thenReturn(claimList);
		ResponseEntity<List<Claim>> claimslist = claimController.getClaims(1);
		assertNotNull(claimslist);

	}

}
