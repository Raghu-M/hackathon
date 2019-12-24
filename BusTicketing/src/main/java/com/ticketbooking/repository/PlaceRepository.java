package com.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{

}
