package com.matrimony.cassini.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.dto.UserAcceptanceRequestDto;
import com.matrimony.cassini.dto.UserResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserInterestRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserInterestServiceImpl implements UserInterestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInterestRepository userInterestRepository;

	@Override
	@Transactional
	public List<User> getAllFilteredUsers(FilterRequestDto filterRequestDto) {
		Optional<User> user = userRepository.findById(filterRequestDto.getUserId());
		if (user.isPresent()) {
			List<User> users = userRepository.findByGenderNot(user.get().getGender());
			List<UserInterest> userInterests = userInterestRepository.findByToUser(user.get());
			for (UserInterest userInterest : userInterests) {
				users = users.stream()
						.filter(user1 -> !(user1.getUserId().equals(userInterest.getFromUser().getUserId())))
						.collect(Collectors.toList());
			}
			List<UserInterest> userInterests1 = userInterestRepository.findByFromUserAndStatus(user.get(), "Requested");
			for (UserInterest userInterest : userInterests1) {
				users = users.stream()
						.filter(user1 -> !(user1.getUserId().equals(userInterest.getToUser().getUserId())))
						.collect(Collectors.toList());
			}
			if (filterRequestDto.getOccupation() != null) {
				users = users.stream().filter(user1 -> user1.getOccupation().equals(filterRequestDto.getOccupation()))
						.collect(Collectors.toList());
			}
			if (filterRequestDto.getReligion() != null) {
				users = users.stream().filter(user1 -> user1.getReligion().equals(filterRequestDto.getReligion()))
						.collect(Collectors.toList());
			}
			if (filterRequestDto.getDateOfBirth() != null) {
				users = users.stream()
						.filter(user1 -> user1.getDateOfBirth().isAfter(filterRequestDto.getDateOfBirth()))
						.collect(Collectors.toList());
			}
			return users;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public List<User> acceptedDetails(Integer userId) {
		List<User> users = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<UserInterest> userMappings = userInterestRepository.findByFromUserAndStatus(user.get(),
					Constant.ACCEPTED);
			for (UserInterest UserMapping : userMappings) {
				users.add(UserMapping.getToUser());
			}
		}
		return users;
	}

	public InterestResponseDto showInterest(InterestRequestDto interestRequestDto) throws UserNotFoundException {
		Optional<User> sender = userRepository.findById(interestRequestDto.getFromUserId());
		Optional<User> receiver = userRepository.findById(interestRequestDto.getToUserId());
		if (!sender.isPresent()) {
			throw new UserNotFoundException(Constant.SENDER_PROFILE_NOT_FOUND);
		}
		if (!receiver.isPresent()) {
			throw new UserNotFoundException(Constant.RECEIVER_PROFILE_NOT_FOUND);
		}
		UserInterest interest = new UserInterest();
		interest.setStatus(Constant.REQUESTED);
		interest.setFromUser(sender.get());
		interest.setToUser(receiver.get());
		interest.setDate(LocalDate.now());
		userInterestRepository.save(interest);
		InterestResponseDto interestResponseDto = new InterestResponseDto();
		interestResponseDto.setMessage(Constant.REQUESTED);
		interestResponseDto.setStatusCode(Constant.OK);
		return interestResponseDto;

	}

	@Override
	@Transactional
	public List<Optional<User>> requestList(Integer userId) throws RequestNotRaisedException {
		Optional<User> currentuser = userRepository.findById(userId);
		List<UserInterest> userInterests = userInterestRepository.findAllUserMappingsByToUserAndStatus(currentuser,
				Constant.REQUESTED);
		List<Optional<User>> users = new ArrayList<>();
		if (userInterests.isEmpty()) {
			return new ArrayList<>();
		} else {
			for (UserInterest userInterest : userInterests) {
				Optional<User> interresteduser = userRepository.findById(userInterest.getFromUser().getUserId());
				users.add(interresteduser);
			}
			return users;
		}

	}

	@Override
	@Transactional
	public UserResponseDto userResponse(UserAcceptanceRequestDto userAcceptanceRequestDto) throws RequestNotRaisedException {
		Optional<User> fromUser = userRepository.findById(userAcceptanceRequestDto.getFromUserId());
		Optional<User> toUser = userRepository.findById(userAcceptanceRequestDto.getToUserId());
		Optional<UserInterest> userMapping = userInterestRepository.findByFromUserAndToUser(toUser.get(),
				fromUser.get());
		UserResponseDto userResponseDto = new UserResponseDto();
		if (!userMapping.isPresent()) {
			throw new RequestNotRaisedException(Constant.REQUEST_NOT_RAISED);
		} else {
			if (userAcceptanceRequestDto.getStatusCode().equals(Constant.ACCEPTED_CODE)) {

				userMapping.get().setStatus(Constant.ACCEPTED);
				userMapping.get().setDate(LocalDate.now());
				userInterestRepository.save(userMapping.get());
				UserInterest acceptedUserMapping = new UserInterest();
				acceptedUserMapping
						.setFromUser(userRepository.findById(userAcceptanceRequestDto.getFromUserId()).get());
				acceptedUserMapping.setToUser(userRepository.findById(userAcceptanceRequestDto.getToUserId()).get());
				acceptedUserMapping.setDate(LocalDate.now());
				acceptedUserMapping.setStatus(Constant.ACCEPTED);
				userInterestRepository.save(acceptedUserMapping);
				userResponseDto.setMessage(Constant.ACCEPTED);
				return userResponseDto;
			} else {
				userMapping.get().setStatus(Constant.REJECTED);
				userMapping.get().setDate(LocalDate.now());
				userInterestRepository.save(userMapping.get());
				userResponseDto.setMessage(Constant.REJECTED);
				return userResponseDto;
			}
		}
	}

}
