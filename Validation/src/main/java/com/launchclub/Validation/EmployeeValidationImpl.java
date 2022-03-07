package com.launchclub.Validation;


import com.launchclub.Dao.EmployeeDao;
import com.launchclub.Dao.EmployeeDaoImpl;
import com.launchclub.Exception.InvalidDateException;
import com.launchclub.Validation.EmployeeValidation;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;

@Component(service = EmployeeValidation.class)
public class EmployeeValidationImpl implements EmployeeValidation {
	
	private static final EmployeeDao EMPLOYEE_DAO = new EmployeeDaoImpl();

	/**
	 * Validate Choice.
	 */
	public boolean validateChoice(final String choice) {
		return ((choice.matches("[aA-dD]")||"YES".equalsIgnoreCase(choice) || "NO".equalsIgnoreCase(choice)
				|| "GOMENU".equalsIgnoreCase(choice) || "EXIT".equalsIgnoreCase(choice))) ? true : false; 
	}	

	/**
	 * Validate employeeId already present.
	 */
	public boolean validateIdPresent(final String employeeId) {
		return (EMPLOYEE_DAO.getAllEmployees().containsKey(employeeId)) ? true : false;
 	}

	/**
	 * Validate the employeeId.
	 */
	public boolean validateEmployeeId(final String employeeId) {
		return (employeeId.matches("[0-9]{1,}|[0-9]{1,}[aA-zZ]{1,}|[aA-zZ]{1,}[0-9]{1,}")) ? true : false; 
	}

	/**
	 * Validate the employeeName.
	 */
	public boolean validateEmployeeName(final String employeeName) {
		return (employeeName.matches("[a-zA-Z\\s]*")) ? true : false;
	}

	public boolean validateEmployeeSalary(final String employeeSalary) {
		return (employeeSalary.matches("(\\d+\\.\\d+)|(\\d+)")) ? true : false;
	}

	/**
	 * Validate the employeePhoneNo.
	 */
	public boolean validateEmployeePhoneNo(final String employeePhoneNo) {
		return (employeePhoneNo.matches("(0|91)?[6-9][0-9]{9}")) ? true : false;
	}

	/**
	 * Validate the employeeDateOfBirth.
	 */
	public boolean validateEmployeeDateOfBirth(final String employeeDateOfBirth) {

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
