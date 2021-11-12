package com.revature.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Item;
import com.revature.services.ItemServices;
import com.revature.models.Offer;
import com.revature.services.OfferServices;
import com.revature.util.ConnectionUtil;
import com.revature.repositories.ItemPostgres;
import com.revature.repositories.OfferPostgres;
import com.revature.services.EmployeeServices;

public class EmployeeController {
	
	private static ItemServices its = new ItemServices();
	private static OfferServices os = new OfferServices();
	private static ItemPostgres itemPostgres = new ItemPostgres();
	
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
				viewOffers(sc);
				sc.nextLine();
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
		its.showInventory();
		System.out.println("Input Item ID:");
		int id = sc.nextInt();
		System.out.println("How Many do you want remaining?:");
		int available = sc.nextInt();
		sc.nextLine();
		Item i = new Item(id, available);
		if(available > 0) {
			ItemPostgres.reorganizeInventory(id, available);
			System.out.println("Successfully Reduced Inventory.");	

		} else if (available == 0) {
			
			itemPostgres.remove(i);
			System.out.println("Item Has Been Removed From the Shop!");
		}
		//		if(i != null) {
//			System.out.println("Product Remaining: " + i.getName() + " " + i.getPrice() + " " + i.getAvailable());
//		}
		employeeMenu(sc);
	}
	
	public static void viewOffers(Scanner sc) {
		List<Offer> offers = OfferServices.getAll();
		for (Offer o : offers) {
			System.out.println(o);
		}
		System.out.println();
		System.out.println("To Accept an Offer Press 1");
		System.out.println("To Reject an Offer Press 2");
		System.out.println("To Return to the Main Menu Press 3:");
		System.out.println();
		String input = sc.nextLine();
		if(input.equals("1")) {
			OfferServices.getAll();
			
			System.out.println("Enter Offer ID:");
			int id = sc.nextInt();
			System.out.println("Enter Item ID:");
			int itemId = sc.nextInt();
			System.out.println("Pending Offer Approval...");

			OfferPostgres.updateById(id, itemId);
			ItemPostgres.reduceStock(itemId);

		} else if (input.equals("2")) {
			System.out.println("What Offer Would You Like To Reject");
			int offerId = sc.nextInt();
			Offer temp = OfferPostgres.getByID(offerId);
			if(temp.isOfferAccepted() == false) {
				OfferPostgres.removeById(offerId);
			}else {
				System.out.println("Can't Remove An Accepted Offer");
			}	
			
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