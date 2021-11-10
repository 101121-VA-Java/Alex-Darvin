package com.revature.repositories;

import com.revature.models.Cart;

public interface CartDao extends GenericDao<Cart>{

	Cart getByID(int customer_id);

}