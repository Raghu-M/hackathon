package com.ticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Seat;
import com.ticketbooking.entity.Ticket;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
	
	List<Seat> findAllByTicket(Optional<Ticket> ticket);

	Seat findSeatById(Integer id);


	
}
