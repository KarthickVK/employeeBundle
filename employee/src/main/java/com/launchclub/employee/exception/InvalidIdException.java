package com.launchclub.employee.exception;

import com.launchclub.exception.CustomException;

public class InvalidIdException extends CustomException {

	public InvalidIdException(String message) {
		super(message);
	}
}
