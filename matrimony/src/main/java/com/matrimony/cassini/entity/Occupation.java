package com.matrimony.cassini.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Occupation {
	
	@Id
	private Integer occupationId;
	private String occupationName;

}
