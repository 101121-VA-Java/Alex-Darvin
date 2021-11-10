package com.revature.controllers;

import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.services.CustomerServices;
import com.revature.services.EmployeeServices;

public class LoginController {
	
	private static CustomerServices cs = new CustomerServices();
	private static EmployeeServices es = new EmployeeServices();
	
	public static void userLogin(Scanner sc) {
		System.out.println("Press 1 for customer, 2 for employee:");
		String user = sc.nextLine();
		
		System.out.println("Please Enter Your Email:");
		String email = sc.nextLine();
		// check to see if email/user exists
		
		System.out.println("Please Enter Your Password:");
		String password = sc.nextLine();
		
		if(user.equals("1")) {
			try {
				cs.login(email, password);
				System.out.println("Log In Successful!");
				CustomerController.customerMenu(sc);
			} catch (LoginException e) {
				System.out.println("Invalid Login, Press 1 to Try Again or Press 2 Register for an Account:");
				String input = sc.nextLine();
				if(input.equals("1")) {
					userLogin(sc);
				} else {
					RegisterController.register(sc);
				}
			}
		}
		if(user.equals("2")) {
			try {
				es.login(email, password);
				EmployeeController.employeeMenu(sc);
			} catch (LoginException e) {
				System.out.println("Invalid Login, Press 1 to Try Again or Press 2 Register for an Account:");
				String input = sc.nextLine();
				if(input.equals("1")) {
					userLogin(sc);
				} else {
					RegisterController.register(sc);
				}
			}
		}
	}
}