package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Item;
import com.revature.services.ItemServices;
import com.revature.models.Offer;
import com.revature.services.OfferServices;
import com.revature.repositories.OfferPostgres;
import com.revature.services.EmployeeServices;

public class EmployeeController {
	
	private static ItemServices its = new ItemServices();
	private static OfferServices os = new OfferServices();
	
	public static void employeeMenu(Scanner sc) {
		// print out menu
		boolean flag = true;
		while(flag) {
			System.out.println();
			System.out.println("Employee Menu");
			System.out.println("1: Add an item to the shop");
			System.out.println("2: Remove an item to the shop");
			System.out.println("3: View Offers on Items");
			System.out.println("4: View Payments");
			System.out.println("5: Exit");
			String input = sc.nextLine();
			switch(input){
			case "1":
				System.out.println("Add an Item to the Shop:");
				flag = false;
				addItemToShop(sc);
				break;
			case "2":
				System.out.println("Remove an Item from the Shop:");
				flag = false;
				removeItemFromShop(sc);
				break;
			case "3":
				System.out.println("View Offers For Items");
				flag = false;
				viewOffers(sc);
				break;
			case "4":
				System.out.println("View Payments");
				viewPayments(sc);
				flag = false;
				break;
			case "5":
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
		System.out.println("How many would you like to add?:");
		int available = sc.nextInt();
		sc.nextLine();
		String offersMade = "Available";
		Item i = new Item(name, price, offersMade, available);
		i.setPrice(price);
		
		i = its.addItem(i);
		System.out.println("Item added: " + i.getName() + " " + i.getPrice() + " " + i.getAvailable());
		employeeMenu(sc);
	}
	
	public static void removeItemFromShop(Scanner sc) {
		System.out.println("Input the item you would like to remove:");
		String name = sc.nextLine();
		System.out.println("Input the amount in inventory you would like to remove:");
		int available = sc.nextInt();
		sc.nextLine();
		Item i = new Item(name, available);
		i = its.removeItem(i);
		System.out.println("Successfully Removed Item.");	
		if(i != null) {
			System.out.println("Product Remaining: " + i.getName() + " " + i.getPrice() + " " + i.getAvailable());
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
		String input = sc.nextLine();
		if(input.equals("1")) {
			System.out.println("Which Offer You Would Like To Accept");
			OfferServices.getAll();
			int offerId = sc.nextInt();
			sc.nextLine();
			// sql query to change accepted to true where offerId = ?
			os.update();
			viewOffers(sc);
		} else if (input.equals("2")) {
			System.out.println("What Offer Would You Like To Reject");
			int offerId = sc.nextInt();
			sc.nextLine();
			os.update();
			viewOffers(sc);
		} else {
			employeeMenu(sc);
		}
	}
	
	public static void viewPayments(Scanner sc) {
		its.showPayments();
		System.out.print("Press Any Number to Return to the Menu:");
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