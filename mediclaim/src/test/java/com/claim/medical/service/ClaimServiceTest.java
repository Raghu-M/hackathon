package com.claim.medical.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medical.entity.Claim;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.repository.ClaimRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClaimServiceTest {

	@InjectMocks
	ClaimServiceImpl claimServiceImpl;
	@Mock
	ClaimRepository claimRepository;

	@Before
	public void setup() {
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

	}

	@Test
	public void testClaimDetails() throws PolicyNotFoundException {
		Long claimId = 9999L;
		Claim claim = new Claim();
		Mockito.when(claimRepository.findByClaimId(claimId)).thenReturn(Optional.of(claim));
		Claim response = claimServiceImpl.viewClaimDetails(claimId);
		assertNotNull(response);
	}

}
