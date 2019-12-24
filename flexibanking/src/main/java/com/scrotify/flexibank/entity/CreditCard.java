package com.scrotify.flexibank.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard {
	
	@Id
	private Long creditCardNumber;
	private Integer cvv;
	private Integer pin;
	private LocalDate expiryDate;
	private Double cardLimit;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private Account account;
	

}
