package com.launchclub.validation;

import com.launchclub.exception.InvalidDateException;

import java.time.LocalDate;

public class Validation {
	
	/**
	 * Validate the employeeId.
	 */
	public static boolean validateId(final String employeeId) {
		return (employeeId.matches("[0-9]{1,}|[0-9]{1,}[aA-zZ]{1,}|[aA-zZ]{1,}[0-9]{1,}")) ? true : false; 
	}

	/**
	 * Validate the employeeName.
	 */
	public static boolean validateName(final String employeeName) {
		return (employeeName.matches("[a-zA-Z\\s]*")) ? true : false;
	}

	public static boolean validateSalary(final String employeeSalary) {
		return (employeeSalary.matches("(\\d+\\.\\d+)|(\\d+)")) ? true : false;
	}

	/**
	 * Validate the employeePhoneNo.
	 */
	public static boolean validatePhoneNo(final String employeePhoneNo) {
		return (employeePhoneNo.matches("(0|91)?[6-9][0-9]{9}")) ? true : false;
	}

	/**
	 * Validate the employeeDateOfBirth.
	 */
	public static boolean validateDate(final String employeeDateOfBirth) {

		try {
			final LocalDate date = LocalDate.parse(employeeDateOfBirth);
			final LocalDate todayDate = LocalDate.now();

			if (todayDate.plusDays(1).isAfter(date)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new InvalidDateException("Please Enter Valid DateOfBirth");
		}
	}
}
