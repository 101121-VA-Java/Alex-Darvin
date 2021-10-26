package com.revature.services;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;

public class CustomerService {

	private CustomerDao cd;
	
	public int addCustomer(Customer c) {
		/*
		 * add business logic here to manipulate e before storage
		 */
		return cd.addCustomer(c);
	}
}