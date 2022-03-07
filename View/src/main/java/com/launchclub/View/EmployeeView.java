package com.launchclub.View;

import java.sql.Date;
import java.util.Scanner;

import com.launchclub.Exception.CustomException;
import com.launchclub.Exception.IdAlreadyFoundException;
import com.launchclub.Model.Employee;
import com.launchclub.Validation.EmployeeValidation;
import com.launchclub.controller.EmployeeController;
import org.osgi.service.component.annotations.Reference;


/**
 * Represents on EmployeeView. this class is used to collect all the user
 * inputs.
 * 
 * @author KarthickV
 */

public class EmployeeView {

	@Reference
	private static EmployeeValidation employeevalidation;
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
			System.out.println("Do you need to Continue?(Yes,No)");
			userChoice = EmployeeView.getUserChoice();
		} while ("yes".equalsIgnoreCase(userChoice));
	}

	/**
	 * Gets menuChoice.
	 */
	private static String getMenuChoice() {
		System.out.println("Enter the choice(a-d):");
		final String choice = SCANNER.next().trim();

		if (employeevalidation.validateChoice(choice)) {
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
		System.out.println("Enter the choice(yes or no):");
		final String choice = SCANNER.next().trim();

		if ("no".equalsIgnoreCase(choice)) {
			System.out.println("Thank you!!!!");
			System.exit(0);
		}

		if (employeevalidation.validateChoice(choice)) {
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
		System.out.println("Enter the employeeId: !!! Do you want to go Mainmenu press goMenu");
		final String employeeId = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(employeeId)) {
			EmployeeView.showMenu();
		}

		if (employeevalidation.validateEmployeeId(employeeId)) {
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
		System.out.println("Enter the employeeName: !!! Do you want to go Mainmenu press goMenu");
		final String employeeName = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(employeeName)) {
			EmployeeView.showMenu();
		}

		if (employeevalidation.validateEmployeeName(employeeName)) {
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
		System.out.println("Enter the employeePhoneNo: !!! Do you want to go Mainmenu press goMenu");
		final String employeePhoneNo = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(employeePhoneNo)) {
			EmployeeView.showMenu();
		}

		if (employeevalidation.validateEmployeePhoneNo(employeePhoneNo)) {
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
		System.out.println("Enter the employeeSalary: !!! Do you want to go Mainmenu press goMenu");
		final String employeeSalary = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(employeeSalary)) {
			EmployeeView.showMenu();
		}
		
		if (employeevalidation.validateEmployeeSalary(employeeSalary)) {
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
		System.out.println("Enter the employeeDateOfBirth(yyyy-MM-dd) !!! Do you want to go Mainmenu press goMenu");
		final String employeeDateOfBirth = SCANNER.next().trim();

		if ("goMenu".equalsIgnoreCase(employeeDateOfBirth)) {
			EmployeeView.showMenu();
		}
		boolean isValidEmployeeDateOfBirth = false;

		try {
			 isValidEmployeeDateOfBirth = employeevalidation.validateEmployeeDateOfBirth(employeeDateOfBirth);
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

		if (employeevalidation.validateIdPresent(employeeId)) {
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

		if (employeevalidation.validateChoice(choice)) {
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

		if (!employeevalidation.validateIdPresent(employeeId)) {
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
