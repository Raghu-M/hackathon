package com.claim.medical.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	Optional<Claim> findByAdmittedDate(LocalDate admittedDate);
	List<Claim> findAllByClaimStatus(String claimStatus);
	Optional<Claim> findByClaimId(Long claimId);

}
