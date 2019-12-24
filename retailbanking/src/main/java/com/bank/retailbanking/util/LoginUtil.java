package com.bank.retailbanking.util;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.LoginResponseDto;

public class LoginUtil {
	
	private LoginUtil() {
		
	}
	
	public static LoginResponseDto loginFailure() {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage(Constant.LOGIN_FAILURE);
		loginResponseDto.setStatusCode(Constant.LOGIN_FAILURE_CODE);
		loginResponseDto.setCustomerId(null);
		return loginResponseDto;
	}

}
