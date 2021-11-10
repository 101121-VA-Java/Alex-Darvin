package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;
import com.revature.repositories.ItemDao;
//import com.revature.repositoriei.ItemList;
import com.revature.repositories.ItemPostgres;
import com.revature.models.Customer;
import com.revature.models.Item;
import com.revature.controllers.CustomerController;

public class ItemServices {

	private static ItemDao id = new ItemPostgres();
	private static CustomerDao cd = new CustomerPostgres();
	private static Scanner sc = new Scanner(System.in);	
	
	public Item addItem(Item i){
		i.setCustomerID(0);
		Item compareItem = this.getItemByName(i.getName());
		if(compareItem != null ){
			int total = compareItem.getAvailable() + i.getAvailable();
			i.setAvailable(total);
			compareItem.setAvailable(total);
			id.update(compareItem);
			return compareItem; 
		} else {
			return id.add(i);	
		}
	}
	
	public Item getItemByName(String name) {
		List<Item> item = id.getAll();
		for(Item i: item) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	public Item removeItem(Item i) {
		Item compareItem = this.getItemByName(i.getName());
		int total = compareItem.getAvailable() - i.getAvailable();
		i.setAvailable(total);
		i.setPrice(compareItem.getPrice());
		i.setOffersMade(compareItem.getOffersMade());
		compareItem.setAvailable(total);
		id.update(compareItem);
		if(total == 0) { 
			id.remove(i);
			return null;
		} else {
			return i;
		}
	}
	
	public void showInventory() {
		List<Item> item = id.getAll();
		int counter = 0;
		for(Item i: item) {
			if(i.getOffersMade().equals("Available")){
				counter += 1;
				System.out.println(counter + ": " + i.getName() + " $"
						+ i.getPrice() + " In Stock: " + i.getAvailable());
			}
		}
	}
	
	public void moveToCart(int choice) {
		List<Item> item = id.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		if(choice > item.size()) {
			System.out.println("Invalid Input, Please Select an Number from the List or 0 to return to the Dashboard:");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 0){
				CustomerController.customerMenu(sc);
			} else {
				moveToCart(choice);
			}
		} else if(choice > 0 && choice < item.size()) {
			int counter = 0;
			Item itemChoice = new Item();
			for(Item i: item) {
				if(i.getOffersMade().equals("Available")){
					counter += 1;
					if(choice == counter) {
						itemChoice = i;
					} 
				}
			}
			System.out.println("Added (1) " + itemChoice.getName()
					+ " to Your Cart");
			int newAvailable = itemChoice.getAvailable() - 1;
			itemChoice.setAvailable(newAvailable);
			for(Customer cust : customers) {
				if(cust.isLoggedIn() == true) {
					customerLoggedIn = cust;
				}
			}
			int cartID = customerLoggedIn.getCustomerID();
			id.update(itemChoice);
			Item cartItem = new Item(itemChoice.getName(), itemChoice.getPrice(), 
					 "Pending", 1, cartID);
			id.add(cartItem);
			System.out.println("Press 1 to Continue Shopping or Press 2 to Return to the Dashboard");
			String input = sc.nextLine();
			if(input.equals("1")) {
				CustomerController.shopInventory(sc);
			} else {
				CustomerController.customerMenu(sc);
			}
			
		}
		
	}
	
	public void showCart() {
		List<Item> item = id.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for(Item i: item) {
			if(i.getOffersMade().equals("Pending") && i.getCustomerID() == customerLoggedIn.getCustomerID()){
				counter += 1;
				System.out.println(counter + ": " + i.getName() + " $"
						+ i.getPrice() + " In Cart: " + i.getAvailable());
			}
		}
	}
	
	public void submitCart() {
		List<Item> item = id.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		for(Item i: item) {
			if(i.getOffersMade().equals("Pending") && i.getCustomerID() == customerLoggedIn.getCustomerID()){
				i.setOffersMade("Submitted");
				id.update(i);
			}
		}
	}
	
	public void removeFromCart(int item) {
		List<Item> items = id.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for(Item i: items) {
			if(i.getOffersMade().equals("Pending") && i.getCustomerID() == customerLoggedIn.getCustomerID()){
				counter += 1;
				if(item == counter) {
					Item removedItem = new Item(i.getName(), i.getPrice(), i.getOffersMade(), i.getAvailable(), i.getCustomerID());
					i.setOffersMade("Available");
					i.setCustomerID(0);
					addItem(i);
					id.remove(removedItem);
					System.out.println("Removed item " + i.getName() + " for $" + i.getPrice() + " from cart.");
				}
			}
		}
	}
	
	public void showHistory() {
		List<Item> item = id.getAll();
		List<Customer> customers = cd.getAll();
		Customer customerLoggedIn = new Customer();
		for(Customer cust : customers) {
			if(cust.isLoggedIn() == true) {
				customerLoggedIn = cust;
			}
		}
		int counter = 0;
		for(Item i: item) {
			if(i.getOffersMade().equals("Owned") && i.getCustomerID() == customerLoggedIn.getCustomerID()){
				counter += 1;
				System.out.println(counter + ": " + i.getName() + " $"
						+ i.getPrice() + " Purchased: " + i.getAvailable());
			}
		}
		System.out.println("Press Any Key to Return to the Dashboard:");
		String input003 = sc.nextLine();
		CustomerController.customerMenu(sc);
	}
	
	public void showOffers() {
		List<Item> item = id.getAll();
		int counter = 0;
		for(Item i: item) {
			if(i.getOffersMade().equals("Submitted")){
				counter += 1;
				System.out.println(counter + ": " + i.getName() + " Offer = $"
						+ i.getPrice() + " In Cart: " + i.getAvailable() + " Customer ID: " + i.getCustomerID());
			}
		}
	}
	
	public void acceptOffer(int item) {
		List<Item> items = id.getAll();
		int counter = 0;
		for(Item i: items) {
			if(i.getOffersMade().equals("Submitted")){
				counter += 1;
				if(item == counter) {
					System.out.println("Accepted offer on " + i.getAvailable() + " " + i.getName() + " for $" + i.getPrice());
					i.setOffersMade("Owned");
					id.update(i);
				}
			}
		}
	}
	
	public void rejectOffer(int item) {
		List<Item> items = id.getAll();
		int counter = 0;
		for(Item i: items) {
			if(i.getOffersMade().equals("Submitted")){
				counter += 1;
				if(item == counter) {
					Item removedItem = new Item( i.getName(), i.getPrice(), i.getOffersMade(), i.getAvailable(), i.getCustomerID());
					i.setOffersMade("Available");
					i.setCustomerID(0);
					addItem(i);
					id.remove(removedItem);
					System.out.println("Rejected offer fo " + i.getName() + "for $" + i.getPrice());
				}
			}
		}
	}
	
	public void showPayments() {
		List<Item> item = id.getAll();
		double total = 0;
		int counter = 0;
		for(Item i: item) {
			if(i.getOffersMade().equals("Owned")) {
				counter += 1;
				System.out.println(counter + i.getName() + " " + i.getAvailable() +  " $" + i.getPrice());
				total += i.getPrice();
			}
		}
		System.out.println("Total Sales = " + total);
	}
	
}