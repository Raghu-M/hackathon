package com.claim.medical.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.constants.Constant;
import com.claim.medical.dto.ApproveClaimRequestDto;
import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.entity.Claim;
import com.claim.medical.entity.PolicyHolder;
import com.claim.medical.entity.User;
import com.claim.medical.exception.AlreadyClaimedException;
import com.claim.medical.exception.InvalidClaimAmountException;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyHolderNotFoundException;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.exception.UserNotFoundException;
import com.claim.medical.repository.ClaimRepository;
import com.claim.medical.repository.PolicyHolderRepository;
import com.claim.medical.repository.UserRepository;

@Service
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	ClaimRepository claimRepository;

	@Override
	public Claim viewClaimDetails(Long claimId) throws PolicyNotFoundException {

		Optional<Claim> claim = claimRepository.findByClaimId(claimId);
		if (claim.isPresent()) {
			return claim.get();
		} else {
			throw new PolicyNotFoundException(Constant.POLICY_NOT_FOUND);
		}
	}

	@Autowired
	PolicyHolderRepository policyHolderRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Long raiseRequest(ClaimRequestDto claimRequestDto) throws PolicyHolderNotFoundException,
			PolicyExpiredException, InvalidClaimAmountException, PolicyNotFoundException, AlreadyClaimedException {

		Claim claim = new Claim();
		Optional<PolicyHolder> policyHolder = policyHolderRepository.findById(claimRequestDto.getPolicyHolderId());
		if (!policyHolder.isPresent()) {
			throw new PolicyHolderNotFoundException(Constant.POLICY_HOLDER_NOT_FOUND);
		} else if (!policyHolder.get().getPolicyNumber().equals(claimRequestDto.getPolicyNumber())) {
			throw new PolicyNotFoundException(Constant.POLICY_NOT_FOUND);
		} else if (!(claimRequestDto.getAdmittedDate().isAfter(policyHolder.get().getStartDate())
				&& claimRequestDto.getAdmittedDate().isBefore(policyHolder.get().getEndDate()))) {
			throw new PolicyExpiredException(Constant.POLICY_EXPIRED);
		} else if (claimRequestDto.getClaimAmount() <= Constant.ZERO_AMOUNT) {
			throw new InvalidClaimAmountException(Constant.CLAIM_AMOUNT_INVALID);
		} else if (claimRepository.findByAdmittedDate(claimRequestDto.getAdmittedDate()).isPresent()) {
			throw new AlreadyClaimedException(Constant.ALREADY_CLAIMED);
		} else {
			claim.setPolicyHolder(policyHolder.get());
			claim.setName(claimRequestDto.getName());
			claim.setAilmentType(claimRequestDto.getAilmentType());
			claim.setClaimDate(LocalDate.now());
			claim.setDiagnosis(claimRequestDto.getDiagnosis());
			claim.setDischargeSummary(claimRequestDto.getDischargeSummary());
			claim.setHospitalName(claimRequestDto.getHospitalName());
			claim.setMedicalFee(claimRequestDto.getMedicalFee());
			claim.setDoctorFee(claimRequestDto.getDoctorFee());
			claim.setClaimStatus(Constant.CLAIM_PENDING_STAGE_1);
			claim.setAdmittedDate(claimRequestDto.getAdmittedDate());
			claim.setDischargeDate(claimRequestDto.getDischargeDate());
			claim.setClaimAmount(claimRequestDto.getClaimAmount());
			claim.setPolicyNumber(claimRequestDto.getPolicyNumber());
			claimRepository.save(claim);
			return claim.getClaimId();
		}
	}

	@Override
	public String approverClaimResponse(ApproveClaimRequestDto approveClaimRequestDto) {
		Optional<User> user = userRepository.findByUserName(approveClaimRequestDto.getUserName());
		Optional<Claim> claim = claimRepository.findById(approveClaimRequestDto.getClaimId());
		if (user.isPresent() && claim.isPresent()) {
			if (approveClaimRequestDto.getApproveStatus().equals(Constant.APPROVE)) {
				if (user.get().getRole().getRoleId().equals(Constant.LEVEL_ONE_APPROVER)) {
					if (claim.get().getClaimAmount() > Constant.MAX_LEVEL_1APPROVE_AMOUNT) {
						claim.get().setClaimStatus(Constant.CLAIM_PENDING_STAGE_2);
						claim.get().setApproverComments(approveClaimRequestDto.getApproverComment());
						claimRepository.save(claim.get());
						return Constant.SUCCESS;
					} else {
						claim.get().setClaimStatus(Constant.CLAIM_APPROVE);
						claim.get().setApproverComments(approveClaimRequestDto.getApproverComment());
						claimRepository.save(claim.get());
						return Constant.SUCCESS;
					}
				} else {
					claim.get().setClaimStatus(Constant.CLAIM_APPROVE);
					claim.get().setApproverComments(approveClaimRequestDto.getApproverComment());
					claimRepository.save(claim.get());
					return Constant.SUCCESS;
				}

			} else if (approveClaimRequestDto.getApproveStatus().equals(Constant.DENY)) {
				claim.get().setClaimStatus(Constant.CLAIM_DENY);
				claim.get().setApproverComments(approveClaimRequestDto.getApproverComment());
				claimRepository.save(claim.get());
				return Constant.SUCCESS;

			} else {
				claim.get().setClaimStatus(Constant.CLAIM_REFER_BACK);
				claim.get().setApproverComments(approveClaimRequestDto.getApproverComment());
				claimRepository.save(claim.get());
				return Constant.SUCCESS;

			}
		} else {
			return "failed";
		}

	}

	@Override
	public List<Claim> getClaims(Integer userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		List<Claim> claims = null;
		if (user.isPresent()) {
			if (user.get().getRole().getRoleId().equals(Constant.LEVEL_ONE_APPROVER)) {
				claims = claimRepository.findAllByClaimStatus(Constant.CLAIM_PENDING_STAGE_1);
				return claims;

			} else {
				claims = claimRepository.findAllByClaimStatus(Constant.CLAIM_PENDING_STAGE_2);
				return claims;
			}
		} else {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}
	}

}
