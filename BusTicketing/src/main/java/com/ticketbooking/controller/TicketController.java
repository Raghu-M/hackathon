package com.ticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ticketbooking.dto.BookingRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.ticketbooking.exceptions.TicketNotFoundException;
import com.ticketbooking.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping
	public ResponseEntity<String> bookTicket(@RequestBody BookingRequestDto bookingRequestDto){
		String result = ticketService.bookTicket(bookingRequestDto);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PutMapping("/{ticketId}")
	public ResponseEntity<String> cancelTicket(@PathVariable("ticketId") Long ticketId) throws TicketNotFoundException{
		return ResponseEntity.ok().body(ticketService.cancelTicket(ticketId));
		
	}

}
