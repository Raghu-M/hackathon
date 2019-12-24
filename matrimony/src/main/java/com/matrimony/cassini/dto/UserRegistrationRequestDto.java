package com.matrimony.cassini.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
	
	private String fullName;
	private LocalDate dateOfBirth;
	private String gender;
	private Double height;
	private Long phoneNumber;
	private String occupation;
	private String qualification;
	private String city;
	private String email;
	private String religion;
	private String motherTongue;
	private String userName;
	private String password;

}
