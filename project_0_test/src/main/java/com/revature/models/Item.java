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

	public Item(String name, double price, String offerStatus, int available, int customerID) {
		super();
		this.name = name;
		this.price = price;
		this.offersMade = offerStatus;
		this.available = available;
		this.customerID = customerID;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getavailable() {
		return available;
	}

	public void setavailable(int available) {
		this.available = available;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOfferStatus() {
		return offersMade;
	}

	public void setOfferStatus(String offersMade) {
		this.offersMade = offersMade;
	}

	
}