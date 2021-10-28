package com.revature.controllers;

import com.revature.services.CustomerService;
import java.util.Scanner;
import com.revature.models.Customer;

public class CustomerController {

	private CustomerService cs = new CustomerService();
	
	
	public void registerCustomer(Scanner sc) {
		System.out.println("Please enter a Name ");
		String name = sc.nextLine();
		System.out.println("Please enter your Username ");
		String username = sc.nextLine();	
		System.out.println("Please enter your Password ");
		String password = sc.nextLine();
		System.out.println("Please enter your Email ");
		String email = sc.nextLine();
		System.out.println("Enter a Credit Card Number: ");
		Integer card_number = sc.nextInt();
		sc.nextLine();
		
		Customer newCustomer = new Customer(name, username, password, email, card_number);
		cs.addCustomer(newCustomer);
		System.out.println("Customer has been registered.");
		
	}
}