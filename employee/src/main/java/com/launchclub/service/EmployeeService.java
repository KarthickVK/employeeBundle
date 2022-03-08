package com.launchclub.service;

import com.launchclub.model.Employee;

import java.util.List;

/**
 * EmployeeService Then only in a method declaration not implementation in thin
 * part.
 * 
 * @author KarthickV
 */
public interface EmployeeService {

	boolean createEmployee(final Employee employee);

	boolean updateEmployee(final Employee employeeDetails);

	boolean deleteEmployee(final String employeeId);

	List<Employee> getAllEmployees();
}
