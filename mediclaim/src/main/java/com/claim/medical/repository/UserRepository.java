package com.claim.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.User;

/**
 * This Repository is having all the querys of the User.
 * 
 * @author yoga
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);

	Optional<User> findByUserNameAndPassword(String userName, String password);
}
