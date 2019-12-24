package com.ticketbooking.service;

import com.ticketbooking.dto.BookingRequestDto;
import com.ticketbooking.exceptions.TicketNotFoundException;

public interface TicketService {

	String cancelTicket(Long ticketId) throws TicketNotFoundException;

	String bookTicket(BookingRequestDto bookingRequestDto);

}
