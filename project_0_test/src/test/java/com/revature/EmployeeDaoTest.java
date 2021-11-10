//package com.revature;
//
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.revature.models.Employee;
//import com.revature.repositories.EmployeeList;
//import com.revature.repositories.EmployeeDao;
//
//public class EmployeeDaoTest {
//
//	private static EmployeeDao ed;
//	
//	@BeforeEach
//	public void setup() {
//		Employee[] employees = {new Employee(0, "Alex", "Darvin", "adarv@gmail.com", null)}; 
//
//		ed = new EmployeeList(employees);
//	}
//	
//	@Test
//	public void getAllEmployees(){
//		Employee[] expected = {new Employee(0, "Alex", "Darvin", "adarv@gmail.com", null)};
//		Employee[] actual = ed.getAllEmployees();
//		assertArrayEquals(expected, actual);
//	}
//	
//	@Test
//	public void addEmployeeValid() {
//		Employee[] expected = {new Employee(0, "Alex", "Darvin", "adarv@gmail.com", null), 
//								new Employee(1, "Al", "A", "Pass", null) };
//		
//		// Adding an employee
//		ed.addEmployee(new Employee(1, "Al", "A", "Pass", null));
//		
//		assertArrayEquals(expected, ed.getAllEmployees());
//	}
//}