package com.matrimony.cassini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.Occupation;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {

}
