package com.cassini.beneficiarymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	long countByCustomer(Customer customer);

	Optional<Beneficiary> findByBeneficiaryAccountAndCustomer(Account account, Customer customer);

	List<Beneficiary> findAllByCustomerAndStatusOrderByBeneficiaryNameAsc(Customer customerId, String status);

	public Optional<Beneficiary> findByBeneficiaryId(Integer beneficiaryId);

}
