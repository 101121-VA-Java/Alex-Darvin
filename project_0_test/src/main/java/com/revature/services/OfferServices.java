package com.revature.services;

import java.util.List;
import com.revature.models.Offer;
import com.revature.repositories.OfferDao;
import com.revature.repositories.OfferPostgres;

public class OfferServices {
	private static OfferDao od = new OfferPostgres();
	
	public static Offer submit(Offer o) {
		return od.add(o);
	}
	
	public static List<Offer> getAll() {
		return od.getAll();
	}
	
	public static boolean update(Offer o) {
		return od.update(o);
	}
}