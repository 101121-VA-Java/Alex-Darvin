package com.revature.repositories;

import com.revature.models.Customer;

public interface CustomerDao {
	Customer[] getAllCustomers(); // returns an array of all of the employees available
	Customer getCustomerById(int id);
	int addCustomer(Customer c); // returns assigned id
	boolean editCustomer(Customer c);  //returns boolean depending on operation success 
	boolean deleteCustomer(int id);  //returns boolean depending on operation success 
}