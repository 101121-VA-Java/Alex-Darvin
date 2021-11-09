package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Item;
import com.revature.services.ItemServices;

public class EmployeeController {
	
	private static ItemServices its = new ItemServices();
	
	public static void employeeMenu(Scanner sc) {
		// print out menu
		boolean flag = true;
		while(flag) {
			System.out.println();
			System.out.println("Employee Dashboard");
			System.out.println("\n1: Add an item to the shop"
								+ "\n2: Remove an item from the shop"
								+ "\n3: View offers for Items"
								+ "\n4: View all payments"
								+ "\n5: Log Out");
			
			String input = sc.nextLine();
			switch(input){
			case "1":
				// Add item
				System.out.println("Add an Item to the Shop:");
				flag = false;
				addItemToShop(sc);
				break;
			case "2":
				// Remove an item from the shop
				System.out.println("Remove an Item from the Shop:");
				flag = false;
				removeItemFromShop(sc);
				break;
			case "3":
				// View Offers
				System.out.println("View Offers For Items");
				flag = false;
				viewOffers(sc);
				break;
			case "4":
				// View Payments
				System.out.println("View Payments");
				viewPayments(sc);
				flag = false;
				break;
			case "5":
				// Log Out
				System.out.println("You Have Successfully Logged Out");
				flag = false;
				FrontController.run();
				break;
			default:
				System.out.println("Invalid Input, Please Select a Valid Option");
				System.out.println();
			}
		
		}
	}
	
	public static void addItemToShop(Scanner sc) {
		System.out.println("Input the name:");
		String name = sc.nextLine();
		System.out.println("Input the price of the item:");
		double price = sc.nextInt();
		sc.nextLine();
		System.out.println("How many would :");
		int available = sc.nextInt();
		sc.nextLine();
		Item i = new Item(name, price, available);
		i = its.addItems(i);
		System.out.println("Item added: " + i.getName() + " " + s.getModel() + " " + s.getPrice() + " " + s.getInStock());
		employeeMenu(sc);
	}
	
	public static void removeItemFromShop(Scanner sc) {
		System.out.println("Input the item you would like to remove:");
		String name = sc.nextLine();
		System.out.println("Input the amount in inventory you would like to remove:");
		int available = sc.nextInt();
		sc.nextLine();
		Item i = new Item(name, available);
		i = its.removeItems(i);
		System.out.println("Successfully Removed Item.");	
		if(i != null) {
			System.out.println("Product Remaining: " + s.getBrand() + " " + s.getModel() + " " + s.getPrice() + " " + s.getInStock());
		}
		employeeMenu(sc);
	}
	
	public static void viewOffers(Scanner sc) {
		its.showOffers();
		System.out.println();
		System.out.print("To Accept an Offer Press 1");
		System.out.print("To Reject an Offer Press 2");
		System.out.print("To Return to the Main Menu Press 3:");
		System.out.println();
		System.out.println();
		String input = sc.nextLine();
		if(input.equals("1")) {
			System.out.println("Enter the Number of the Item You Would Accept");
			int item = sc.nextInt();
			sc.nextLine();
			ss.acceptOffer(item);
			viewOffers(sc);
		} else if (input.equals("2")) {
			System.out.println("Enter the Number of the Item You Would Reject");
			int item = sc.nextInt();
			sc.nextLine();
			its.rejectOffer(item);
			viewOffers(sc);
		} else {
			employeeMenu(sc);
		}
	}
	
	public static void viewPayments(Scanner sc) {
		its.showPayments();
		System.out.print("Press Any Number to Return to the Dashboard:");
		int numInput = sc.nextInt();
		sc.nextLine();
		if(numInput > 0) {
			employeeMenu(sc);
		} else {
			System.out.println("Invalid Input");
			viewPayments(sc);
		}
		
	}
	
	
	
}