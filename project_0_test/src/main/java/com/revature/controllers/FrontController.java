package com.revature.controllers;

import java.util.Scanner;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.CustomerController;

public class FrontController {

	private Scanner sc; 
	private UserController uc;
	private EmployeeController ec;
	private CustomerController cc;
	
	public FrontController() {
		sc = new Scanner(System.in);
		uc = new UserController();
	}
	
	public void run() {
		boolean run = true;
		
		while(run) {
			System.out.println("Please select an option:");
			System.out.println("1: Register an employee");
			System.out.println("2: Login");
			System.out.println("3: Create a customer account");
			System.out.println("4: Exit");
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1":
				uc.registerEmployee(sc);
				// TODO: if an employee is not registered, display a different message
				break;
			case "2":
				break;
			case "3":
				uc.registerCustomer(sc);
				break;
			case "4":
				run = false;
				break;
			default:
				System.out.println("Invalid input.");
			}
		}
		
		sc.close();
	}
}