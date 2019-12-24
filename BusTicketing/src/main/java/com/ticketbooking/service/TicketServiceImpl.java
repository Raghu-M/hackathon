package com.ticketbooking.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.entity.Ticket;
import com.ticketbooking.exceptions.TicketNotFoundException;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.TicketRepository;

import com.ticketbooking.constants.Constant;
import com.ticketbooking.dto.BookingRequestDto;
import com.ticketbooking.entity.Seat;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	SeatRepository seatRepository;

	@Override
	@Transactional
	public String bookTicket(BookingRequestDto bookingRequestDto) {
		Ticket ticket = new Ticket();
		ticket.setPassengerName(bookingRequestDto.getPassengerName());
		ticket.setCancelStatus(Constant.CANCEL_STATUS);
		ticket.setPassengerPhonNumber(bookingRequestDto.getPassengerMobileNumber());
		ticket.setBusId(seatRepository.findById(bookingRequestDto.getSeatIds().get(0)).get().getBus().getBusId());
		ticketRepository.save(ticket);
		for (Integer seatId : bookingRequestDto.getSeatIds()) {
			Optional<Seat> seat = seatRepository.findById(seatId);
			if (seat.isPresent()) {
				seat.get().setStatus(Constant.SEAT_STATUS);
				seat.get().setTicket(ticket);
				seatRepository.save(seat.get());
			}
		}

		return Constant.BOOKING_SUCCESS;
	}

	public String cancelTicket(Long ticketId) throws TicketNotFoundException {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		if (ticket.isPresent()) {
			ticket.get().setCancelStatus(Constant.SEAT_AVAILABLE_STATUS_YES);
			ticketRepository.save(ticket.get());
			List<Seat> seats = seatRepository.findAllByTicket(ticket);
			seats.forEach(seat -> {
				seat.setStatus(Constant.AVAILABLE);
				seat.setTicket(null);
				seatRepository.save(seat);
			});
			return Constant.SUCCESS;
		} else {
				throw new TicketNotFoundException(Constant.TICKET_NOT_FOUND);
		}
	}

}
