package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public interface GenericDao<U> {
	
	// needs to add, getById, getAll, update, and delete
	
	U add(U u);
	U getByUsername(String username);
	List<U> getAll();
	boolean update(U u);
	U remove(U u);
}