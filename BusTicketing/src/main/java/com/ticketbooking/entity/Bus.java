package com.ticketbooking.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;
	private String travelsName;
	private String source;
	private String destination;
	private double price;
	private LocalTime arrivalTime;
	private LocalTime destinationTime;
	private double travelTime;

}
