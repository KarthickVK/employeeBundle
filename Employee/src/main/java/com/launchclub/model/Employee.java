package com.launchclub.model;

import java.sql.Date;

/**
 * Represents an Employee.
 * 
 * @author KarthickV
 */
public class Employee {

	private String employeeId;
	private String employeeName;
	private String employeePhoneNo;
	private String employeeSalary;
	private Date employeeDateOfBirth;

	public Employee() {
		super();
	}

	public Employee(String employeeId, String employeeName, String employeePhoneNo, String employeeSalary,
                    Date employeeDateOfBirth) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePhoneNo = employeePhoneNo;
		this.employeeSalary = employeeSalary;
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}

	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Date getEmployeeDateOfBirth() {
		return employeeDateOfBirth;
	}

	public void setEmployeeDateOfBirth(Date employeeDateOfBirth) {
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	/**
	 * Display the employee details.
	 */
	public String toString() {
		final String string = String.format("EmployeeId:%s \n EmployeeName:%s \n EmployeePhoneNo:%s \n EmployeeSalary:%s \n EmployeeDateOfbirth:%s \n\n", employeeId, employeeName,
				employeePhoneNo, employeeSalary, employeeDateOfBirth);
		return string;
	}
}
