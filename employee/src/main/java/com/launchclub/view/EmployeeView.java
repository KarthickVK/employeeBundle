package com.launchclub.view;

import com.launchclub.Exception.CustomException;
import com.launchclub.UserInputs;
import com.launchclub.controller.EmployeeController;
import com.launchclub.exception.IdAlreadyFoundException;
import com.launchclub.model.Employee;
import com.launchclub.validation.Validation;
import com.launchclub.validation.EmployeeValidation;

import java.sql.Date;
import java.util.Scanner;

/**
 * Represents on EmployeeView. this class is used to collect all the user
 * inputs.
 * 
 * @author KarthickV
 */
public class EmployeeView {

    private static final EmployeeController EMPLOYEE_CONTROLLER = new EmployeeController();
	private static final Scanner SCANNER = new Scanner(System.in) ;

	/**
	 * Select menu.
	 */
	public static void showMenu() {
		String userChoice;
		
		do {
			System.out.println("EmployeeManagement: \na.CREATE \nb.UPDATE \nc.DELETE \nd.SHOW");
			final String choice = EmployeeView.getMenuChoice();

			if ("a".equalsIgnoreCase(choice)) {
				EmployeeView.createEmployee();
			} else if ("b".equalsIgnoreCase(choice)) {
				EmployeeView.updateEmployee();
			} else if ("c".equalsIgnoreCase(choice)) {
				EmployeeView.deleteEmployee();
			} else if ("d".equalsIgnoreCase(choice)) {
				EmployeeView.getAllEmployees();
			}
			userChoice = UserInputs.getInputs("Do you need to Continue?(Yes,No)");
		} while ("yes".equalsIgnoreCase(userChoice));
	}

	/**
	 * Gets menuChoice.
	 */
	private static String getMenuChoice() {
		final String choice = UserInputs.getInputs("Enter the choice(a-d):");

		if (EmployeeValidation.validateChoice(choice)) {
			return choice;
		} else {
			System.out.println("Please Enter Valid Choice:");
			return EmployeeView.getMenuChoice();
		}
	}

	/**
	 * Gets userChoice.
	 */

	private static String getUserChoice() {
		final String choice = UserInputs.getInputs("Enter the choice(yes or no):");

		if ("no".equalsIgnoreCase(choice)) {
			System.out.println("Thank you!!!!");
			System.exit(0);
		}

		if (EmployeeValidation.validateChoice(choice)) {
			return choice;
		} else {
			System.out.println("Please Enter Valid Choice:");
			return EmployeeView.getUserChoice();
		}
	}

	/**
	 * Gets employeeId to the user.
	 */
	private static String getEmployeeId() {
		final String employeeId = UserInputs.getInputs("Enter the employeeId: !!! Do you want to go Mainmenu press goMenu");

		if ("goMenu".equalsIgnoreCase(employeeId)) {
			EmployeeView.showMenu();
		}

		if (Validation.validateId(employeeId)) {
			return employeeId;
		} else {
			System.out.println("Please Enter Valid Id");
			return EmployeeView.getEmployeeId();
		}
	}

	/**
	 * Gets EmployeeName to the user
	 */
	private static String getEmployeeName() {
		final String employeeName = UserInputs.getInputs("Enter the employeeName: !!! Do you want to go Mainmenu press goMenu");

		if ("goMenu".equalsIgnoreCase(employeeName)) {
			EmployeeView.showMenu();
		}

		if (Validation.validateName(employeeName)) {
			return employeeName;
		} else {
			System.out.println("Please Enter Valid EmployeeName");
			return EmployeeView.getEmployeeName();
		}
	}

	/**
	 * Gets EmployeePhoneNo to the user.
	 */
	private static String getEmployeePhoneNo() {
		final String employeePhoneNo = UserInputs.getInputs("Enter the employeePhoneNo: !!! Do you want to go Mainmenu press goMenu");

		if ("goMenu".equalsIgnoreCase(employeePhoneNo)) {
			EmployeeView.showMenu();
		}

		if (Validation.validatePhoneNo(employeePhoneNo)) {
			return employeePhoneNo;
		} else {
			System.out.println("Please Enter Valid PhoneNo");
			return EmployeeView.getEmployeePhoneNo();
		}
	}

	/**
	 * Gets EmployeeSalary to the user.
	 */
	private static String getEmployeeSalary() {
		final String employeeSalary = UserInputs.getInputs("Enter the employeeSalary: !!! Do you want to go Mainmenu press goMenu");

		if ("goMenu".equalsIgnoreCase(employeeSalary)) {
			EmployeeView.showMenu();
		}
		
		if (Validation.validateSalary(employeeSalary)) {
			return employeeSalary;
		} else {
			System.out.println("Please Enter Valid EmployeeSalary");
			return EmployeeView.getEmployeeSalary();
		}
	}

	/**
	 * Gets EmployeeDateOfBirth to the user.
	 */
	private static Date getEmployeeDateOfBirth() {
		final String employeeDateOfBirth = UserInputs.getInputs("Enter the employeeDateOfBirth(yyyy-MM-dd) !!! Do you want to go Mainmenu press goMenu");

		if ("goMenu".equalsIgnoreCase(employeeDateOfBirth)) {
			EmployeeView.showMenu();
		}
		boolean isValidEmployeeDateOfBirth = false;

		try {
			 isValidEmployeeDateOfBirth = Validation.validateDate(employeeDateOfBirth);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		if (isValidEmployeeDateOfBirth) {
			return Date.valueOf(employeeDateOfBirth);
		} else {
			return EmployeeView.getEmployeeDateOfBirth();
		}
	}

	/**
	 * Gets employeeDetails.
	 */
	private static void createEmployee() {
		final String employeeId = EmployeeView.getEmployeeId();

		if (EmployeeValidation.validateIdPresent(employeeId)) {
			System.out.println("EmpoyeeId Already present \n Please Enter Valid EmployeeId");
			EmployeeView.createEmployee();
			EmployeeView.showMenu();
		}

		final String employeeName = EmployeeView.getEmployeeName();
		final String employeePhoneNo = EmployeeView.getEmployeePhoneNo();
		final String salary = EmployeeView.getEmployeeSalary();
		final Date employeeDateOfBirth = EmployeeView.getEmployeeDateOfBirth();
		final Employee employee = new Employee(employeeId, employeeName, employeePhoneNo, salary, employeeDateOfBirth);

		try {
			EMPLOYEE_CONTROLLER.createEmployee(employee);
			System.out.println("Data Added Successfully!!!");
		} catch (IdAlreadyFoundException exception) {
			System.out.println(exception);
			EmployeeView.createEmployee();
		}
	}

	/**
	 * Update EmployeeDetails.Then user choose the one properties to call the
	 * related method.
	 */
	private static String getUpdateChoice() {
		System.out.println("Enter the choice(yes or no or goMenu or Exit):");
		final String choice = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(choice)) {
			EmployeeView.showMenu();
		}

		if ("exit".equalsIgnoreCase(choice)) {
			System.out.println("Thank you!!!!");
		}

		if (EmployeeValidation.validateChoice(choice)) {
			return choice;
		} else {
			System.out.println("Please Enter Valid Choice:");
			return EmployeeView.getUpdateChoice();
		}
	}

	/**
	 * Update EmployeeDetails.
	 */
	private static void updateEmployee() {
		String employeeName = null;
		String employeePhoneNo = null;
		String employeeSalary = null;
		Date employeeDateOfBirth = null;
		final String employeeId = EmployeeView.getEmployeeId();

		if (!EmployeeValidation.validateIdPresent(employeeId)) {
			System.out.println("Please Enter Valid EmployeeId");
			EmployeeView.updateEmployee();
			EmployeeView.showMenu();
		}
		final Employee employeeDetails = new Employee();
		System.out.println("do you want to change EmployeeName?\t yes or no or goMenu or Exit");

		if ("yes".equalsIgnoreCase(EmployeeView.getUpdateChoice())) {
			employeeName = EmployeeView.getEmployeeName();
		}
		System.out.println("do you want to change EmployeePhoneNo?\t yes or no or goMenu or Exit");

		if ("yes".equalsIgnoreCase(EmployeeView.getUpdateChoice())) {
			employeePhoneNo = EmployeeView.getEmployeePhoneNo();
		}
		System.out.println("do you want to change EmployeeSalary?\t yes or no or goMenu or Exit");

		if ("yes".equalsIgnoreCase(EmployeeView.getUpdateChoice())) {
			employeeSalary = EmployeeView.getEmployeeSalary();
		}
		System.out.println("do you want to change EmployeeDateOfBirth?\t yes or no or goMenu or Exit");

		if ("yes".equalsIgnoreCase(EmployeeView.getUpdateChoice())) {
			employeeDateOfBirth = EmployeeView.getEmployeeDateOfBirth();
		}

		employeeDetails.setEmployeeId(employeeId);
		employeeDetails.setEmployeeName(employeeName);
		employeeDetails.setEmployeePhoneNo(employeePhoneNo);
		employeeDetails.setEmployeeSalary(employeeSalary);
		employeeDetails.setEmployeeDateOfBirth(employeeDateOfBirth);

		try {
			if (EMPLOYEE_CONTROLLER.updateEmployee(employeeDetails)) {
				System.out.println("Data Updated Successfully!!!");
			}
		} catch (CustomException exception) {
			System.out.println(exception);
			EmployeeView.updateEmployee();
		}
	}

	/**
	 * Delete employeeDetails.
	 */
	private static void deleteEmployee() {
		final String employeeId = EmployeeView.getEmployeeId();

		try {
			EMPLOYEE_CONTROLLER.deleteEmployee(employeeId);
			System.out.println("Data Deleted Successfully!!!");
		} catch (CustomException exception) {
			System.out.println(exception);
			EmployeeView.deleteEmployee();
		}
	}

	/**
	 * Show all the employeeDetails.
	 */
	private static void getAllEmployees() {
		System.out.println(EMPLOYEE_CONTROLLER.showAllEmployees());
	}
}
