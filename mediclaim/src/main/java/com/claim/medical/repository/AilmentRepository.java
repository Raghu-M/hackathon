package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Ailment;

@Repository
public interface AilmentRepository extends JpaRepository<Ailment, Integer>{

}
