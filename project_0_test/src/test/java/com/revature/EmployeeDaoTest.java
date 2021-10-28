package com.revature;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeArray;
import com.revature.repositories.EmployeeDao;

public class EmployeeDaoTest {

	private static EmployeeDao ed;
	
	@BeforeEach
	public void setup() {
		Employee[] employees = {new Employee(0, "Alex", "Al", "AlPass")}; 

		ed = new EmployeeArray(employees);
	}
	
	@Test
	public void getAllEmployees(){
		Employee[] expected = {new Employee(0, "Alex", "Al", "AlPass")};
		Employee[] actual = ed.getAllEmployees();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void addEmployeeValid() {
		Employee[] expected = {new Employee(0, "Alex", "Al", "AlPass"), 
								new Employee(1, "Al", "A", "Pass") };
		
		// Adding an employee
		ed.addEmployee(new Employee(1, "Al", "A", "Pass"));
		
		assertArrayEquals(expected, ed.getAllEmployees());
	}
}