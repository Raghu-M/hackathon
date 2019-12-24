package com.matrimony.cassini.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {

	List<UserInterest> findByFromUserAndStatus(User fromUser, String status);

	List<UserInterest> findAllUserMappingsByToUserAndStatus(Optional<User> currentuser, String requested);

	Optional<UserInterest> findByFromUserAndToUser(User fromUser, User toUser);

	List<UserInterest> findByToUser(User user);

}
