package com.revature.models;

import java.util.Objects;

public class Offer {
	private int offerId;
	private int itemId;
	private String customerEmail;
	private float amount;
	private boolean offerAccepted;

	
	public Offer(int offerId, int itemId, String customerEmail, float amount, boolean offerAccepted) {
		super();
		this.offerId = offerId;
		this.itemId = itemId;
		this.customerEmail = customerEmail;
		this.amount = amount;
		this.offerAccepted = offerAccepted;
	}
	
	public Offer(int itemId, String customerEmail, float amount, boolean offerAccepted) {
		super();
		this.itemId = itemId;
		this.customerEmail = customerEmail;
		this.amount = amount;
		this.offerAccepted = offerAccepted;
	}


	public Offer(String customerEmail, int itemId, float amount) {
		// TODO Auto-generated constructor stub
		super();
		this.itemId = itemId;
		this.customerEmail = customerEmail;
		this.amount = amount;
	}

	
	public int getOfferId() {
		return offerId;
	}


	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}

	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	
	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public boolean isOfferAccepted() {
		return offerAccepted;
	}


	public void setOfferAccepted(boolean offerAccepted) {
		this.offerAccepted = offerAccepted;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, customerEmail, itemId, offerAccepted, offerId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount) && customerEmail == other.customerEmail
				&& itemId == other.itemId && offerAccepted == other.offerAccepted && offerId == other.offerId;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", itemId=" + itemId + ", customerEmail=" + customerEmail + ", amount="
				+ amount + ", offerAccepted=" + offerAccepted + "]";
	}
	

}