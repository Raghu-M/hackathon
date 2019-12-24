package com.ticketbooking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDto {
	
	private List<Integer> seatIds;
	private String passengerName;
	private Long passengerMobileNumber;
	

}
