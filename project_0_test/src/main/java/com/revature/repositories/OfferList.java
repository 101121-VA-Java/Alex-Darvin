package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Offer;

public class OfferList implements OfferDao{
	
	private List<Offer> Offers;
	
	public OfferList() {
		Offers = new ArrayList<Offer>();
		
	}

	@Override
	public Offer add(Offer o) {
		Offers.add(o);
		return o;
	}
	
	@Override
	public List<Offer> getAll(){
		return Offers;
	}
	
	@Override
	public Offer remove(Offer o) {
		Offers.remove(o);
		return o;
	}

	@Override
	public boolean update(Offer o) {
		//TODO Auto-generated method stub
		return true;
	}

	@Override
	public Offer getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}