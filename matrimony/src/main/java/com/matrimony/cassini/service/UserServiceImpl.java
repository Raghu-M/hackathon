package com.matrimony.cassini.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNameAlreadyExistException;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		Optional<User> user = userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		if (user.isPresent()) {
			return user;
		} else {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}

	}

	@Override
	public RegisterResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) throws UserNameAlreadyExistException {
		if(userRepository.findByUserName(userRegistrationRequestDto.getUserName()).isPresent()) {
			throw new UserNameAlreadyExistException(Constant.USER_NAME_ALREADY_EXIST);
		}
		User user = new User();
		user.setFullName(userRegistrationRequestDto.getFullName());
		user.setPhoneNumber(userRegistrationRequestDto.getPhoneNumber());
		user.setCity(userRegistrationRequestDto.getCity());
		user.setDateOfBirth(userRegistrationRequestDto.getDateOfBirth());
		user.setEmail(userRegistrationRequestDto.getEmail());
		user.setGender(userRegistrationRequestDto.getGender());
		user.setHeight(userRegistrationRequestDto.getHeight());
		user.setMotherTongue(userRegistrationRequestDto.getMotherTongue());
		user.setOccupation(userRegistrationRequestDto.getOccupation());
		user.setPassword(userRegistrationRequestDto.getPassword());
		user.setQualification(userRegistrationRequestDto.getQualification());
		user.setReligion(userRegistrationRequestDto.getReligion());
		user.setUserName(userRegistrationRequestDto.getUserName());
		File file = new File("C:/Users/User1/Desktop/jaanu boyfriend.jpg");
		byte[] imageData = new byte[(int) file.length()];
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setImage(imageData);
		userRepository.save(user);
		Optional<User> user1 = userRepository.findById(user.getUserId());
		byte[] newimage = user1.get().getImage();
		try{
		    FileOutputStream fos = new FileOutputStream("C:/Users/User1/Desktop/JananiVirat.jpg"); 
		    fos.write(newimage);
		    fos.close();
		}catch(Exception e){
		    e.printStackTrace();
		}
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setMessage("success");
		registerResponseDto.setStatusCode(Constant.OK);
		return registerResponseDto;
	}

}
