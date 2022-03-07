package com.launchclub.Validation;

public interface EmployeeValidation {

     boolean validateChoice(final String choice);

     boolean validateIdPresent(final String employeeId);

     boolean validateEmployeeId(final String employeeId);

     boolean validateEmployeeName(final String employeeName);

     boolean validateEmployeeSalary(final String employeeSalary);

     boolean validateEmployeePhoneNo(final String employeePhoneNo);

     boolean validateEmployeeDateOfBirth(final String employeeDateOfBirth);
}
