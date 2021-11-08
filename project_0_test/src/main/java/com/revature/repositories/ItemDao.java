package com.revature.repositories;

import com.revature.models.Item;

public interface ItemDao extends GenericDao<Item> {

	Item getByID(int id);

}