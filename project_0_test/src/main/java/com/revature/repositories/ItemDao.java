package com.revature.repositories;

import java.util.List;

import com.revature.models.Item;

public interface ItemDao extends GenericDao<Item> {

	Item getByID(int id);

	List<Item> getAvailable();

}