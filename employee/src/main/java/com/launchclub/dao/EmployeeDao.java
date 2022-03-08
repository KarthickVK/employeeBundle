package com.launchclub.dao;

import com.launchclub.model.Employee;

import java.util.Map;


/**
 * EmployeeDao Employee Data Object.
 * 
 * @author KarthickV
 */
public interface EmployeeDao {

	boolean createEmployee(final Employee employee);

	boolean updateEmployee(final Employee employeeDetails);

	boolean deleteEmployee(final String employeeId);

	Map<String, Employee> getAllEmployees();
}
