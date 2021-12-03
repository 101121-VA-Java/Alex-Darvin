package com.revature.services;

import java.util.Arrays;

import com.revature.repositories.DaoFactory;
import com.revature.repositories.UserDao;
import com.revature.models.User;
import com.revature.models.Role;

public class AuthServices {

	private UserDao ud;

	// Retrieving an instance of UserDao
	public AuthServices() {
		ud = DaoFactory.getDAOFactory().getUserDao();
	}

	/**
	 * Service method to login an employee based on username/password
	 * @param String username, String password
	 * @return String token if credentials are valid, null otherwise
	 */
	// mimicking token behavior
	public String login(String username, String password) {
		
		String token = null;

		// retrieves a user based on username passed in
		User principal = ud.getByUsername(username);

		if (principal != null && principal.getPassword().equals(password)) {
			/*
			 *  poor token implementation, for example's sake
			 *  	- based on this token, a user can be authenticated when making a request
			 *  	- user-id:role
			 */
			token = principal.getId() + ":" + principal.getRole().getRoleId();
		}

		return token;
	}
	
	/**
	 * Service method to check the permission of the user to access certain functionalities
	 * @param String token, Role... allowedRoles
	 * @return true if a user is authenticated and has permission, false otherwise
	 */
	public boolean checkPermission(String token, Role... allowedRoles) {
		System.out.println(token);
		/*
		 * Behavior to identify user from token
		 */
		// this indicates that a user is not authenticated
		if(token == null) {
			return false;
		}
		
		String[] info = token.split(":"); 
		// retrieve user id
		int token_id = Integer.parseInt(info[0]);
		// retrieve user role
		int token_role = Integer.parseInt(info[1]);
		
		User principal = ud.getById(token_id);
		
		boolean allowed_role = allowedRoles.length < 1;
		for(Role r : Arrays.asList(allowedRoles)) {
			if(r.getRoleId() == token_role)  {
				allowed_role = true;
				break;
			}
		}
		
		if (principal != null && token_role == principal.getRole().getRoleId()	// Authentication of user: make sure user is logged in
				&& allowed_role) {	// Authorization of user: make sure user has the permissions to use the functionality
			System.out.println("passed:");
			return true;
		}
		
		System.out.println("not passed: " + principal.getRole().getRoleId() + " - " + allowedRoles.length);
		
		return false;
	}
}
