package com.launchclub.controller;

import com.launchclub.Model.Employee;
import com.launchclub.Service.EmployeeService;
import com.launchclub.Service.EmployeeServiceImplVersion2;

import java.util.List;

/**
 * EmployeeController class is used to Control all the request and response.
 * Then controller collect the all the user details .
 * 
 * @author KarthickV
 */
public class EmployeeController {

	private static final EmployeeService EMPLOYEE_SERVICE_VERSION2 = new EmployeeServiceImplVersion2();

	public boolean createEmployee(final Employee employee) {
		return EMPLOYEE_SERVICE_VERSION2.createEmployee(employee);
	}

	public boolean updateEmployee(final Employee employeeDetails) {
		return EMPLOYEE_SERVICE_VERSION2.updateEmployee(employeeDetails);
	}

	public boolean deleteEmployee(final String employeeId) {
		return EMPLOYEE_SERVICE_VERSION2.deleteEmployee(employeeId);
	}

	public List<Employee> showAllEmployees() {
		return EMPLOYEE_SERVICE_VERSION2.getAllEmployees();
	}
}
