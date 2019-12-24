package com.scrotify.insurance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policy_holder")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(initialValue = 11, allocationSize = 1, name = "policyHolderId")
public class PolicyHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "policyHolderId" )
	private Integer policyHolderId;
	private String policyHolderName;
	private String gender;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private String email;
	private String nomineeName;
	private String relationShipWithNominee;

}
