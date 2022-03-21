package com.launchclub.employee.controller;

import com.launchclub.employee.model.Employee;
import com.launchclub.employee.service.EmployeeService;
import com.launchclub.employee.service.EmployeeServiceImplVersion2;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeController class is used to Control all the request and response.
 * Then controller collect the all the user details .
 * 
 * @author KarthickV
 */
public class EmployeeController {

	private static final EmployeeService EMPLOYEE_SERVICE_VERSION2 = EmployeeServiceImplVersion2.getInstance();
    private static EmployeeController employeeController;

    private EmployeeController(){

    }

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

	public List<Employee> pagination(final int page,final int limit) {
		List<Employee> list = EMPLOYEE_SERVICE_VERSION2.getAllEmployees();

		int end;
		int start = ((page-1)*limit);

		if(limit == 1){
			end = start+1;
		}else{
			end = start+limit;
		}

		if(start < list.size() && end < list.size()){
			return list.subList(start, end);
		}
		if(start < list.size() && (start+limit) >= list.size()){
			return list.subList(start, list.size());
		}
		return new ArrayList<>();
	}

	public static EmployeeController getInstance() {

		if (employeeController == null)
		{
			employeeController = new EmployeeController();
		}
		return employeeController;
	}
}

