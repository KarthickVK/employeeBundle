package com.launchclub.employee.exception;

import com.launchclub.exception.CustomException;

public class IdAlreadyFoundException extends CustomException {

	public IdAlreadyFoundException(String message) {
		super(message);
	}
}	
