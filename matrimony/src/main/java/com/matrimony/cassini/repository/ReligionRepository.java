package com.matrimony.cassini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.Religion;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Integer>{

}
