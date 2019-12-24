package com.scrotify.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.insurance.entity.PurchasePolicy;

@Repository
public interface PurchasePolicyRepository extends JpaRepository<PurchasePolicy, Integer> {

	List<PurchasePolicy> findByPolicyHolderPolicyHolderId(Integer policyHolderId);

	List<PurchasePolicy> findTop10ByStatusOrderByPurchasePolicyIdDesc(String status);

}