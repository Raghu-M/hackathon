package com.scrotify.insurance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_policy")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(initialValue = 11, allocationSize = 1, name = "purchasePolicyId")
public class PurchasePolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "purchasePolicyId" )
	private Integer purchasePolicyId;
	private LocalDate purchaseDate;
	private String status;

	@ManyToOne
	@JoinColumn(name = "policy_holder_id")
	private PolicyHolder policyHolder;
	@ManyToOne
	@JoinColumn(name = "policy_id")
	private Policy policy;

}
