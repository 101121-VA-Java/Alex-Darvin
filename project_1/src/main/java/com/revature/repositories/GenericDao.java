package com.revature.repositories;

import java.util.List;

public interface GenericDao<U> {
	U add(U u);
	List<U> getAll();
	U getById(int id);
	boolean remove(U u);
	boolean update(U u);
}