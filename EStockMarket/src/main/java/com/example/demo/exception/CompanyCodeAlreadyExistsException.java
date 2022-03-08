package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Company Code already exists!")
public class CompanyCodeAlreadyExistsException extends Exception{

//	private String message;
//	private HttpStatus status;
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public HttpStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(HttpStatus status) {
//		this.status = status;
//	}
//
//	public CompanyCodeAlreadyExistsException() {
//		this.message = message;
//		this.status = status;
//	}

}
