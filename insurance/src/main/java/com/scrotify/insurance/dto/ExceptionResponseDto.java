package com.scrotify.insurance.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
    private String message;

    public ExceptionResponseDto(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ExceptionResponseDto() {
        super();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
