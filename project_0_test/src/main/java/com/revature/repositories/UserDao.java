package com.revature.repositories;

import com.revature.models.User;

public interface UserDao extends GenericDao<User>{

	User getById(int i);
	
}