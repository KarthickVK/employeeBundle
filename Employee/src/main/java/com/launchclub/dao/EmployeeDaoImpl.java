package com.launchclub.dao;

import com.launchclub.Dao.ConnectDataBase;
import com.launchclub.Dao.EmployeeDao;
import com.launchclub.Exception.AccessfailedException;
import com.launchclub.Model.Employee;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * EmployeeDaoImpl
 * 
 * @author KarthickV
 */
public class EmployeeDaoImpl implements EmployeeDao {

	/**
	 * Create the mySql insert statement.
	 */
	public boolean createEmployee(final Employee employee) {
		final String query = "insert into employeedetails (employeeId, employeeName, employeePhoneNo, employeeSalary, employeeDateOfBirth, isactive) values (?, ?, ?, ?, ?, true)";

		try (Connection connection = ConnectDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			final Date startDate = new Date(employee.getEmployeeDateOfBirth().getTime());

			preparedStatement.setString(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getEmployeeName());
			preparedStatement.setString(3, employee.getEmployeePhoneNo());
			preparedStatement.setString(4, employee.getEmployeeSalary());
			preparedStatement.setDate(5, startDate);

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception exception) {
			throw new AccessfailedException("Database Access Failed");
		}
	}

	/**
	 * Create the mySql update statement.
	 */
	public boolean updateEmployee(final Employee employeeDetails) {
		final StringBuilder stringBuilder = new StringBuilder();
		String query = stringBuilder.append("update employeedetails set").toString();
		boolean hasNextColumn = false;
		int employeeIdIndex = 1;
		int employeeNameIndex = 1;
		int employeeSalaryIndex = 1;
		int emolyeePhoneNoIndex = 1;
		int employeeDateOfBirthIndex = 1;
		int count = 0;

		try (Connection connection = ConnectDataBase.getConnection();) {

			if (employeeDetails.getEmployeeName() != null) {
				query = stringBuilder.append(" employeeName = ?").toString();
				hasNextColumn = true;
				count += 1;
			}

			if (employeeDetails.getEmployeePhoneNo() != null) {

				if (hasNextColumn) {
					query = stringBuilder.append(",").toString();
					emolyeePhoneNoIndex = count + 1;
				}
				query = stringBuilder.append(" employeePhoneNo = ?").toString();
				hasNextColumn = true;
				count += 1;
			}

			if (employeeDetails.getEmployeeSalary() != null) {

				if (hasNextColumn) {
					query = stringBuilder.append(",").toString();
					employeeSalaryIndex = count + 1;
				}
				query = stringBuilder.append(" employeeSalary = ?").toString();
				hasNextColumn = true;
				count += 1;
			}

			if (employeeDetails.getEmployeeDateOfBirth() != null) {

				if (hasNextColumn) {
					query = stringBuilder.append(",").toString();
					employeeDateOfBirthIndex = count + 1;
				}
				query = stringBuilder.append(" employeeDateOfBirth = ?").toString();
				hasNextColumn = true;
				count += 1;
			}
			query = stringBuilder.append(" where employeeId = ?").toString();
			employeeIdIndex = count + 1;
			PreparedStatement prepareStatement = connection.prepareStatement(query);

			if (employeeDetails.getEmployeeName() != null) {
				prepareStatement.setString(employeeNameIndex, employeeDetails.getEmployeeName());
			}

			if (employeeDetails.getEmployeePhoneNo() != null) {
				prepareStatement.setString(emolyeePhoneNoIndex, employeeDetails.getEmployeePhoneNo());
			}

			if (employeeDetails.getEmployeeSalary() != null) {
				prepareStatement.setString(employeeSalaryIndex, employeeDetails.getEmployeeSalary());
			}

			if (employeeDetails.getEmployeeDateOfBirth() != null) {
				prepareStatement.setDate(employeeDateOfBirthIndex, employeeDetails.getEmployeeDateOfBirth());
			}
			prepareStatement.setString(employeeIdIndex, employeeDetails.getEmployeeId());
			return prepareStatement.executeUpdate() > 0;
		} catch (Exception exception) {
			throw new AccessfailedException("Database Access Failed");
		}
	}

	/**
	 * Create the delete statement.
	 */
	public boolean deleteEmployee(final String employeeId) {
		final String query = "update employeedetails set isactive = false where employeeId = ?";

		try (Connection connection = ConnectDataBase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, employeeId);

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception exception) {
			throw new AccessfailedException("Database Access Failed");
		}
	}

	/**
	 * Create the mySql select statement.
	 */
	public Map<String, Employee> getAllEmployees() {
		final Map<String, Employee> employeeMap = new HashMap<String, Employee>();
		final String query = "select * from employeedetails where isactive = true";

		try (Connection connection = ConnectDataBase.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				final String employeeId = resultSet.getString("employeeId");
				final String employeeName = resultSet.getString("employeeName");
				final String employeePhoneNo = resultSet.getString("employeePhoneNo");
				final String employeeSalary = resultSet.getString("employeeSalary");
				final Date employeeDateOfBirth = resultSet.getDate("employeeDateOfBirth");
				final Employee employee = new Employee(employeeId, employeeName, employeePhoneNo, employeeSalary,
						employeeDateOfBirth);

				employeeMap.put(employee.getEmployeeId(), employee);
			}
			return employeeMap;
		} catch (Exception exception) {
			throw new AccessfailedException("Database Access Failed");
		}
	}
}
