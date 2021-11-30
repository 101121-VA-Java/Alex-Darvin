package com.revature.repositories;

import com.revature.models.User;

public interface UserDao extends GenericDao<User>{
	public User getByUsername(String username);

	public User getUserById(int token_id);
}