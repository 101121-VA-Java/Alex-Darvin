package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.CustomerServices;
import com.revature.models.Employee;
import com.revature.services.EmployeeServices;
import com.revature.exceptions.EmailTakenException;

public class RegisterController {
	
	private static CustomerServices cs = new CustomerServices();
	
	public static void register(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1: Register as a new Customer");
			System.out.println("2: Register as a new Employe");
			System.out.println("3: Return to the Main Menu");
			
			String input = sc.nextLine();
			switch(input){
			case "1":
				System.out.println("Customer Registration:");
				System.out.println();
				registerCustomer(sc);
				flag = false;
				break;
			case "2":
				System.out.println("Employee Registration:");
				System.out.println();
				registerEmployee(sc);
				flag = false;
				break;
			case "3":
				FrontController.run();
				flag = false;
				break;
			default:
				System.out.println("Invalid input, please select a valid option.");
				System.out.println();
			}
		}
	}
	
	public static void registerCustomer(Scanner sc) {
		
		System.out.println("Please Enter Create Your Username:");
		String username = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		
		String password = passwordSet(sc);
		
		try {
			Customer c = new Customer(username, email, password);
			cs.addCustomer(c);
		} catch (EmailTakenException e) {
			System.out.println("Email is Already in use! Please login or register a different email address...");
		}
		
		System.out.println("Returning to the main menu...");
		FrontController.run();
		
	}

	private static EmployeeServices es = new EmployeeServices();
	
	public static void registerEmployee(Scanner sc) {
		
		System.out.println("Please Enter Your Employee ID Number:");
		int employeeID = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Please Enter Your First Name:");
		String firstName = sc.nextLine();
		
		System.out.println("Please Enter Your Last Name:");
		String lastName = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		
		String password = passwordSet(sc);
		
		Employee newEmployee = new Employee(employeeID, firstName, lastName, email, password);
		
		try {
			newEmployee = es.addEmployee(newEmployee);
			System.out.println("Welcome " + newEmployee.getFirstName() + "! Registration Successful");
		} catch (EmailTakenException e) {
			System.out.println("An Account with this email already exists. Please try to register again or login to your account.");
		}
		
		System.out.println("Returning to the main menu...");
		FrontController.run();
		
	}

	public static String passwordSet(Scanner sc) {
		System.out.println("Create Your Password");
		String password = sc.nextLine();
		
		return password;
	}
}