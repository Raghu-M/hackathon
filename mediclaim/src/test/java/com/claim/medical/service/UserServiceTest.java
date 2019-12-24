package com.claim.medical.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.entity.Claim;
import com.claim.medical.entity.Role;
import com.claim.medical.entity.User;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.repository.ClaimRepository;
import com.claim.medical.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private ClaimRepository claimRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;
	@InjectMocks
	private ClaimServiceImpl claimServiceImpl;

	LoginRequestDto loginRequestDto = null;
	User users = null;
	Claim claim = null;
	Role role = null;
	List<Claim> claimlist = new ArrayList<Claim>();

	@Before
	public void setUp() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("yoga");
		loginRequestDto.setPassword("india");
		users = new User();
		users.setUserName("yoga");
		users.setPassword("india");
		claim = new Claim();
		claim.setAilmentType("fever");
		claim.setApproverComments("approved");
		claim.setClaimAmount(20009.9);
		claim.setDiagnosis("stage");
		claim.setClaimStatus("Approved");
		claim.setClaimId(534634L);
		claim.setDoctorFee(345.90);
		claim.setPolicyNumber(56456456L);
		claim.setName("yoga");
		claim.setMedicalFee(454.54);
		claim.setHospitalName("Narayana");
		claim.setDischargeSummary("Discharged");
		claim.setDischargeDate(LocalDate.now());
		claim.setAdmittedDate(LocalDate.now());
		claim.setClaimDate(LocalDate.now());
		claimlist.add(claim);
	}

	@Test
	public void userLoginTest() throws UserNotFoundException {
		role = new Role();
		role.setRoleId(1);
		users.setRole(role);
		Mockito.when(userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(users));
		User user = userServiceImpl.userLogin(loginRequestDto);
		assertNotNull(user);
	}

	@Test
	public void testgetclaim() throws UserNotFoundException {
		role = new Role();
		role.setRoleId(2);
		users.setRole(role);
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(users));
		List<Claim> claimlist = claimServiceImpl.getClaims(1);
		assertNotNull(claimlist);
	}

}
