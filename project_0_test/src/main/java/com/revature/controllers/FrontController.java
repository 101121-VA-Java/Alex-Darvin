package com.revature.controllers;

import java.util.Scanner;
import com.revature.controllers.EmployeeController;
//import com.revature.controllers.CustomerController;

public class FrontController {

	public static Scanner sc = new Scanner(System.in);
	
	public static void run() {
		boolean run = true;
		
		while(run) {
			System.out.println("Hello Adventurer! Please select an option:");
			System.out.println("1: Create a new account!");
			System.out.println("2: Login");
			System.out.println("3: Exit");
			
			String choice = sc.nextLine();
			
			//register employee method should come from RegisterController
			//register customer should come from RegisterController
			
			//login employee method should come from LoginController
			//login customer should come from LoginController
			
			switch(choice) {
			case "1":
				RegisterController.register(sc);
				// TODO: if an employee is not registered, display a different message
				break;
			case "2":
				LoginController.userLogin(sc);
				break;
			case "3":
				run = false;
				break;
			default:
				System.out.println("Invalid input. Please try again to access our supplies!");
				run();
			}
		}
		
		sc.close();
	}
}