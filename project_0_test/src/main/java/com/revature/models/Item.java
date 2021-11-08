package com.revature.models;

public class Item {

	private String itemName;
	private double price;
	private String offerStatus;
	private int inStock;
	private int customerID;
	
	public Item() {
		super();
	}
	
	public Item(String itemName, int inStock) {
		super();
		this.itemName = itemName;
		this.inStock = inStock;
	}
	
	public Item(String itemName, double price, String offerStatus, int inStock) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.offerStatus = offerStatus;
		this.inStock = inStock;
	}

	public Item(String itemName, double price, String offerStatus, int inStock, int customerID) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.offerStatus = offerStatus;
		this.inStock = inStock;
		this.customerID = customerID;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	
}