package com.matrimony.cassini.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAcceptanceRequestDto {
	
	private Integer fromUserId;
	private Integer toUserId;
	private Integer statusCode;

}
