package com.revature.models;

import java.util.Objects;

public class Offer {
	private int offerId;
	private int itemId;
	private int customerId;
	private float amount;
	private boolean offerAccepted;

	
	public Offer(int offerId, int itemId, int customerId, float amount, boolean offerAccepted) {
		super();
		this.offerId = offerId;
		this.itemId = itemId;
		this.customerId = customerId;
		this.amount = amount;
		this.offerAccepted = offerAccepted;
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


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
		return Objects.hash(amount, customerId, itemId, offerAccepted, offerId);
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
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount) && customerId == other.customerId
				&& itemId == other.itemId && offerAccepted == other.offerAccepted && offerId == other.offerId;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}