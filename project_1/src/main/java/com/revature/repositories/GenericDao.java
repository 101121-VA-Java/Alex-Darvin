package com.revature.repositories;

import java.util.List;

public interface GenericDao<T> {
	List<T> getAll();
	T getById(int id);
	int add(T t);
	boolean edit(T t);
	boolean deleteById(int id);
}