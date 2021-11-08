package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;

public class CustomerList implements CustomerDao {

	private List<Customer> customers;
	
	public CustomerList() {
		customers = new ArrayList<>();
	}
	
	@Override
	public Customer add(Customer u) {
		u.setCustomerID(customers.size());
		customers.add(u);
		return u;
	}
	
	@Override
	public Customer getByID(int id) {
		for(Customer c: customers) {
			if(c.getCustomerID() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Customer> getAll(){
		return customers;
	}
	
	@Override
	public boolean update(Customer u) {
		Customer temp = getByID(u.getCustomerID());
		if(temp == null || temp.equals(u)) {
			return false;
		}
		customers.set(u.getCustomerID(), u);
		return true;
	}
	
	@Override
	public Customer remove(Customer u) {
		customers.remove(u);
		return u;
	}
	
}