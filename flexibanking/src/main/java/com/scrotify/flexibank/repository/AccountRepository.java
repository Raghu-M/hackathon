package com.scrotify.flexibank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByCustomer(Customer customerId);
}
