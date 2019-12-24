package com.ticketbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer seatNumber;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bus_id")
	private Bus bus;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	private String status;

}
