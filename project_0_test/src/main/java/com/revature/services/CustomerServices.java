package com.revature.services;

import java.util.List;

import com.revature.repositories.CustomerDao;
//import com.revature.repositories.CustomerList;
import com.revature.repositories.CustomerPostgres;
import com.revature.exceptions.LoginException;
//import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.EmailTakenException;
import com.revature.models.Customer;

public class CustomerServices {

	private static CustomerDao cd = new CustomerPostgres();
	
	public Customer addCustomer(Customer c) throws EmailTakenException{
		Customer newCustomer = this.getCustomerByEmail(c.getEmail());
		if(newCustomer != null) {
			throw new EmailTakenException();
		}
		return cd.add(c); 
	}
	
	public Customer getCustomerByEmail(String email) {
		List<Customer> customers = cd.getAll();
		for(Customer c : customers) {
			if(c.getEmail().equals(email)) {
				return c;
			}
		}
		return null;
	}
		
	public Customer login(String email, String password) throws LoginException{
		Customer ct = this.getCustomerByEmail(email);
		if(ct == null || !ct.getPassword().equals(password)) {
			throw new LoginException();
		}
		ct.setLoggedIn(true);
		cd.update(ct);
		return ct;
	}
	
	public void logOut() {
		List<Customer> customers = cd.getAll();
		for (Customer ct : customers) {
			if(ct.isLoggedIn() == true) {
				ct.setLoggedIn(false);
			}
		}
	}
	
	public static void main(String[] args) throws LoginException {
		CustomerServices cs = new CustomerServices();
		Customer ct = cs.login("test@test.com", "123");
		System.out.println(ct.getEmail());
	}
}