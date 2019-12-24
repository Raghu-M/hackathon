package com.matrimony.cassini.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Religion {
	
	@Id
	private Integer religionId;
	private String religionName;

}
