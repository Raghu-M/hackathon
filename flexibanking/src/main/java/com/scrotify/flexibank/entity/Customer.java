package com.scrotify.flexibank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private LocalDate dateOfBirth;
	private Long mobileNo;
	private Double salary;
	private String emailId;
	private String address;
	private String userName;
	private String password;

	public static String fullName(String firstName, String lastName) {
		return firstName.concat(" " + lastName);
	}

}
