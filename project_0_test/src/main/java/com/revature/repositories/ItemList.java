package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Item;

public class ItemList implements ItemDao{
	
	private List<Item> Items;
	
	public ItemList() {
		Items = new ArrayList<>();
	}

	@Override
	public Item add(Item u) {
		Items.add(u);
		return u;
	}
	
	@Override
	public List<Item> getAll(){
		return Items;
	}
	
	@Override
	public Item remove(Item u) {
		Items.remove(u);
		return u;
	}

	@Override
	public Item getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Item u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	
}