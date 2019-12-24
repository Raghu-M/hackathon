package com.claim.medical.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ailment {

	@Id
	private Integer ailmentId;
	private String ailmentType;

}
