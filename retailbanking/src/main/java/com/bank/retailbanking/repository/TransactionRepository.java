package com.bank.retailbanking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	

	List<Transaction> getAllByAccountAndTransactionDateBetween(Account accountNumber, LocalDate startDate,
			LocalDate endDate);

	List<Transaction> findTop5ByAccountOrderByTransactionIdDesc(Optional<Account> accountNumber);

}
