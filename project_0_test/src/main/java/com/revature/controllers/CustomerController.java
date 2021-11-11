package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.services.OfferServices;
import com.revature.services.ItemServices;
import com.revature.services.CustomerServices;

public class CustomerController {
	
	private static ItemServices its = new ItemServices();
	private static CustomerServices cs = new CustomerServices();
	private static OfferServices os = new OfferServices();
	
	public static void customerMenu(Scanner sc) {
		// print out menu
		boolean flag = true;
		while(flag){
			System.out.println();
			System.out.println("Welcome to the Adventurers' Shop!:");
			System.out.println("1: View Shop");
			System.out.println("2: View Your Cart");
			System.out.println("3: View Your Order History");
			System.out.println("4: Log Out");
			
			String input = sc.nextLine();
			switch(input){
			case "1":
				// view shop method
				System.out.println("Browse Our Wares Andventurer!");
				flag = false;
				shopInventory(sc);
				break;
			case "2":
				// View Cart
				System.out.println("Viewing Your Cart:");
				flag = false;
				viewCart(sc);
				break;
			case "3":
				// View Order History
				System.out.println("Make An Offer For An Item");
				orderHistory();
				flag = false;
				break;
			case "4":
				// Log Out
				cs.logOut();
				System.out.println("You Have Successfully Logged Out");
				FrontController.run();
				flag = false;
				break;
			default:
				System.out.println("Invalid Input, Please Select an Option 1-4.");
				System.out.println();
			}
		}
	}
	
	
	public static void shopInventory(Scanner sc) {
		System.out.println();
		its.showInventory();
		System.out.println();
		System.out.println("Please Enter the Item Number For the Item You Would Like to Place an Offer For:");
		System.out.println("Or Press 0 to Return to the Customer Menu:");
		int choice = sc.nextInt();
		int itemId = sc.nextInt();
		sc.nextLine();
		if(choice == 0){
			customerMenu(sc);
		} else {
			//change moveToCart to add an offer to the database
			System.out.println("Enter Your Customer ID:");
			int customerId = sc.nextInt();
			System.out.println("How Much Would You Like To Offer?");
			float amount = sc.nextFloat();
			boolean accepted = false;
			Offer o = new Offer(customerId, itemId, amount, accepted);
			OfferServices.submit(o);
			System.out.println("Offer Pending Approval");
			
		}
		
	}
	
//	public static void viewCart(Scanner sc) {
//		its.showCart();
//		System.out.print("To Checkout the Items in your Cart Press 1, To Remove an Item Press 2 Or Press 3 to Return to the Menu:");
//		String input = sc.nextLine();
//		if(input.equals("1")) {
//			System.out.println("Cart Submitted");
//			its.submitCart();
//			customerMenu(sc);
//		} else if(input.equals("2")) {
//			System.out.println("Enter the Number of the Item You Would Remove:");
//			int item = sc.nextInt();
//			sc.nextLine();
//			its.removeFromCart(item);
//			viewCart(sc);
//		} else {
//			customerMenu(sc);
//		}
//	}
	
	public static void orderHistory() {
		its.showHistory();
	}
	
}