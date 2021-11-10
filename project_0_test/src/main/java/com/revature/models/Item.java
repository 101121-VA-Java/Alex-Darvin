package com.revature.models;

public class Item {

	private String name;
	private double price;
	private String offersMade;
	private int available;
	private int customerID;
	
	public Item() {
		super();
	}
	
	public Item(String name, int available) {
		super();
		this.name = name;
		this.available = available;
	}
	
	public Item(String name, double price, String offersMade, int available) {
		super();
		this.name = name;
		this.price = price;
		this.offersMade = offersMade;
		this.available = available;
	}

	public Item(String name, double price, String offersMade, int available, int customerID) {
		super();
		this.name = name;
		this.price = price;
		this.offersMade = offersMade;
		this.available = available;
		this.customerID = customerID;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOffersMade() {
		return offersMade;
	}

	public void setOffersMade(String offersMade) {
		this.offersMade = offersMade;
	}

	public void setItemID(int id) {
		// TODO Auto-generated method stub
		
	}
	
}