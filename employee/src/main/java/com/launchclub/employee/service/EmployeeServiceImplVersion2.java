package com.launchclub.employee.service;

import com.launchclub.employee.exception.IdAlreadyFoundException;
import com.launchclub.employee.exception.InvalidIdException;
import com.launchclub.employee.dao.EmployeeDao;
import com.launchclub.employee.dao.EmployeeDaoImpl;
import com.launchclub.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeServiceImplVersion2
 * 
 * @author KarthickV
 */
public class EmployeeServiceImplVersion2 implements EmployeeService {

	private static final EmployeeDao EMPLOYEE_DAO = EmployeeDaoImpl.getInstance();
	private static EmployeeService employeeService;

	private EmployeeServiceImplVersion2(){

	}

	public static EmployeeService getInstance(){

		if(employeeService == null){
			employeeService = new EmployeeServiceImplVersion2();
		}
		return employeeService;
	}
	
	/**
	 * EmployeeDetails are created.
	 */

	public boolean createEmployee(final Employee employee) {

		if (EMPLOYEE_DAO.createEmployee(employee)) {
			return true;
		} else {
			throw new IdAlreadyFoundException("Id Already Found");
		}
	}

	/**
	 * Updated EmployeeDetails.
	 */
	public boolean updateEmployee(final Employee employeeDetails) {

		if (EMPLOYEE_DAO.updateEmployee(employeeDetails)) {
			return true;
		} else {
			throw new InvalidIdException("please Enter the valid Id");
		}
	}

	/**
	 * Delete EmployeeDetails.
	 */
	public boolean deleteEmployee(final String employeeId) {

		if (EMPLOYEE_DAO.deleteEmployee(employeeId)) {
			return true;
		} else {
			throw new InvalidIdException("Please Enter the valid Id");
		}
	}

	/**
	 * Get EmployeeDetails
	 */
	public List<Employee> getAllEmployees() {
		return new ArrayList<Employee>(EMPLOYEE_DAO.getAllEmployees().values());
	}
}
