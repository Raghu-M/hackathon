package com.matrimony.cassini.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "mapId", allocationSize = 1)
public class UserInterest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapId")
	private Integer mapId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "from_user_id")
	private User fromUser;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "to_user_id")
	private User toUser;
	private String status;
	private LocalDate date;

}
