package com.revature.services;

import java.util.List;

import com.revature.exceptions.EmployeeExistsException;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class UserServices {
	
	private UserDao ud = new UserPostgres();
	
	public List<User> getAllUser(){
		return ud.getAllEmployee();
		
	}
	
	public User addEmployee(User employee) throws EmployeeExistsException {
		User newEmp = this.getEmployeeByUsername(employee.getUsername());
		if(newEmp != null) {
			throw new EmployeeExistsException();
		}
		return ud.addEmployee(employee);
	}
	
	private User getEmployeeByUsername(String username) {
		List<User> employees = ud.getAllEmployee();
		for(User e : employees) {
			if(e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
	}
	
	public boolean updateEmployeer(User employee) {
		return ud.updateEmployee(employee);
		
	}
	
	public User login(String username, String password) throws LoginException {
		List<User> employees = ud.getAllEmployee();
		for(User i : employees) {
			if (i.getUsername().equals(username)&& i.getPassword().equals(password)) {
				return i;
				}
			}
		throw new LoginException();
		}
}