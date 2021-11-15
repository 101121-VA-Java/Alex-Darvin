package com.revature.repositories;


import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface UserDao {

	boolean loginUser (User u);
	
	User viewUserInfo(User u);	
	User updateUserInfo(User u);
	
	User viewAllEmployees();	
	
}