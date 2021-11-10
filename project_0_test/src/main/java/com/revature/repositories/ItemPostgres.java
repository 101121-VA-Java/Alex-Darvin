package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Item;
import com.revature.utilities.ConnectionUtil;

public class ItemPostgres implements ItemDao {

	private Item makeNewItem(ResultSet rs) {

		try {
			int ItemID = rs.getInt("s_id");
			String brand = rs.getString("s_brand");
			String model = rs.getString("s_model");
			Double price = rs.getDouble("s_price");
			int inStock = rs.getInt("s_in_stock");
			
			return new Item(brand, model, price, inStock, ItemID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Item getByID(int id) {
		String sql = "select * from Items where s_id = ? ";
		Item s = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return makeNewItem(rs);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public List<Item> getAll() {
		String sql = "select * from Items;";
		List<Item> Items = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Items.add(makeNewItem(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return Items;
	}

	@Override
	public List<Item> getInStock() {
		String sql = "select * from Items where s_in_stock > 0;";
		List<Item> Items = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				Items.add(makeNewItem(rs));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return Items;

	}

	@Override
	public Item add(Item Items) {
		String sql = "insert into Items (s_brand, s_model, s_price, s_in_stock) "
				+ "values (?, ?, ?, ?) returning s_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, Items.getBrand());
			ps.setString(2, Items.getModel());
			ps.setDouble(3, Items.getPrice());
			ps.setInt(4, Items.getInStock());


			ResultSet rs = ps.executeQuery();
			ps.executeUpdate();

			if(rs.next()) {
				Items.setItemID(rs.getInt(1));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return Items;
	}

	public boolean update(Item Items) {
		String sql = "update Items set s_brand = ?, s_model = ?, s_price = ?, s_in_stock = ? "
				+ "where s_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, Items.getBrand());
			ps.setString(2, Items.getModel());
			ps.setDouble(3, Items.getPrice());
			ps.setInt(4, Items.getInStock());
			ps.setInt(5, Items.getItemID());

			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rowsChanged > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Item remove(Item Items) {
		String sql = "delete from Items where s_id = ?;";
		int rowsChanged = -1;
		int id = Items.getItemID();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rowsChanged > 0) {
			return null;
		} else {
			return Items;
		}

	}

}