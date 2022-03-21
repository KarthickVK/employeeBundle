package com.launchclub.employee.controller;

import com.launchclub.employee.model.Employee;

import java.util.List;

public interface ControllerService {

    boolean postEmployee(final Employee employee);

    boolean putEmployee(final Employee employeeDetails);

    boolean deleteEmployee(final String employeeId);

    List<Employee> getEmployees(final int start, final int size);
}
