package com.scrotify.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.insurance.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}
