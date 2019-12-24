package com.scrotify.insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.insurance.entity.PolicyHolder;

@Repository
public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {

	Optional<PolicyHolder> findByMobileNumber(Long mobileNumber);

}
