package com.revature.repositories;

import com.revature.models.Customer;

public class CustomerArray implements CustomerDao {
private Customer[] customers;
	
	public CustomerArray() {
		super();
	}
	public CustomerArray(Customer[] customers) {
		this.customers = customers;
	}
	@Override
	public Customer[] getAllCustomers() {
		return this.customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCustomer(Customer c) {
		
		// Create a temp array of size Customers + 1
		Customer[] temp = new Customer[customers.length +1 ];
		
		int i = 0;
		
		for(; i < customers.length; i++) {
			temp[i] = customers[i];
		}
		/*
		 *  i is the value of the last index of the new array (Customers.length)
		 *  set i to be the id for the new Customer
		 */
		c.setId(i);
		
		// adding the new Customer to the array
		temp[i] = c;
		
		/*
		 * assign the newly created array to Customers
		 */
		customers = temp;
		
		return i;
	}

	@Override
	public boolean editCustomer(Customer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}