package com.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{
	
	

}
