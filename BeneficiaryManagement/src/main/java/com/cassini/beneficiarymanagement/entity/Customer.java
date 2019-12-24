package com.cassini.beneficiarymanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

	@Id
	private Integer customerId;
	private String customerName;
	private LocalDate dateOfBirth;
	private String address;
	private String userName;
	private String password;
	private Long phoneNumber;
	private String emailId;

}
