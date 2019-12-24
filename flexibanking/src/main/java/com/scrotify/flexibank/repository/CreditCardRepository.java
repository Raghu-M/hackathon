package com.scrotify.flexibank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexibank.entity.Account;
import com.scrotify.flexibank.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	Optional<CreditCard> findByAccount(Account account);

}
