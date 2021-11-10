package com.revature.models;

public class Cart {
	
	private int customerID;
	private String itemName;
	private float itemPrice;
	private String offersMade;
	private int inCart;

	public Cart() {
		super();
	}

	public Cart(int customerID, String itemName, float itemPrice, String offersMade, int inCart) {
		super();
		this.customerID = customerID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.offersMade = offersMade;
		this.inCart = inCart;
	}

	public int getCartID() {
		return customerID;
	}

	public void setCartID(int customerID) {
		this.customerID = customerID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getOffersMade() {
		return offersMade;
	}

	public void setOffersMade(String offersMade) {
		this.offersMade = offersMade;
	}

	public int getInCart() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setInCart(int inCart) {
		this.inCart = inCart;
	}
	
}