package com.matrimony.cassini.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "userId", allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId")
	private Integer userId;
	private String fullName;
	private LocalDate dateOfBirth;
	private String gender;
	private Double height;
	private String occupation;
	private String qualification;
	private String city;
	private Long phoneNumber;
	private String email;
	private String religion;
	private String motherTongue;
	private String userName;
	private String password;
	private byte[] image;
}
