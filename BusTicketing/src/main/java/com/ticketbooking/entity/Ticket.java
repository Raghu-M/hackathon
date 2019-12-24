package com.ticketbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@SequenceGenerator(name = "ticketNumber", initialValue = 987654, allocationSize = 1)
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketNumber")
	private Long ticketId;
	private Integer busId;
	private String cancelStatus;
	private String passengerName;
	private Long passengerPhonNumber;

}
