package com.launchclub.employee.view;

import com.launchclub.employee.controller.EmployeeController;
import com.launchclub.employee.exception.IdAlreadyFoundException;
import com.launchclub.exception.CustomException;

import com.launchclub.employee.model.Employee;
import com.launchclub.userinputs.UserInputs;
import com.launchclub.validation.Validation;
import com.launchclub.employee.validation.EmployeeValidation;
import org.apache.log4j.Logger;

import java.sql.Date;

/**
 * Represents on EmployeeView. this class is used to collect all the user
 * inputs.
 * 
 * @author KarthickV
 */
public class EmployeeView {

    private static final EmployeeController EMPLOYEE_CONTROLLER = EmployeeController.getInstance();
	private static final Logger LOGGER = Logger.getLogger(EmployeeView.class);

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
			LOGGER.warn("Please Enter Valid Choice:");
			System.out.println("hello");
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
			LOGGER.warn("Please Enter Valid Choice:");
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
			LOGGER.warn("Please Enter Valid Id");
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
			LOGGER.warn("Please Enter Valid EmployeeName");
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
			LOGGER.warn("Please Enter Valid PhoneNo");
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
			LOGGER.warn("Please Enter Valid EmployeeSalary");
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
			LOGGER.error(exception);
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
			LOGGER.warn("EmpoyeeId Already present \n Please Enter Valid EmployeeId");
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
			LOGGER.info("Data Added Successfully!!!");
		} catch (IdAlreadyFoundException exception) {
			LOGGER.error(exception);
			EmployeeView.createEmployee();
		}
	}

	/**
	 * Update EmployeeDetails.Then user choose the one properties to call the
	 * related method.
	 */
	private static String getUpdateChoice() {
		final String choice = UserInputs.getInputs("Enter the choice(yes or no or goMenu or Exit):");

		if ("goMenu".equalsIgnoreCase(choice)) {
			EmployeeView.showMenu();
		}

		if ("exit".equalsIgnoreCase(choice)) {
			System.out.println("Thank you!!!!");
		}

		if (EmployeeValidation.validateChoice(choice)) {
			return choice;
		} else {
			LOGGER.warn("Please Enter Valid Choice:");
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
			LOGGER.warn("Please Enter Valid EmployeeId");
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
				LOGGER.info("Data Updated Successfully!!!");
			}
		} catch (CustomException exception) {
			LOGGER.error(exception);
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
			LOGGER.info("Data Deleted Successfully!!!");
		} catch (CustomException exception) {
			LOGGER.error(exception);
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
