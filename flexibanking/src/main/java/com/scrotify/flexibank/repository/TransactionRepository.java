package com.scrotify.flexibank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> getAllByAccountAndTransactionDateBetween(Account account, LocalDate startDate,
			LocalDate endDate);

	List<Transaction> findTop5ByAccountOrderByTransactionIdDesc(Account accountNumber);

}
