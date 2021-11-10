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
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {

	private Item makeNewItem(ResultSet rs) {

		try {
			int i_id = rs.getInt("i_id");
			String i_name = rs.getString("name");
			double i_price = rs.getDouble("i_price");
			int i_available = rs.getInt("i_available");
			
			return new Item(i_id, i_name, i_price, i_available);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Item getByID(int id) {
		String sql = "select * from Items where i_id = ? ";
		Item i = null;

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
		return i;
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
	public List<Item> getAvailable() {
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
		String sql = "insert into Items (i_name, i_price, i_available) "
				+ "values (?, ?, ?, ?) returning i_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, Items.getName());
			ps.setDouble(3, Items.getPrice());
			ps.setInt(4, Items.getAvailable());


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
		String sql = "update Items set name = ?, i_price = ?, i_available = ? "
				+ "where i_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(2, Items.getName());
			ps.setDouble(3, Items.getPrice());
			ps.setInt(4, Items.getAvailable());
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
		String sql = "delete from Items where i_id = ?;";
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

	@Override
	public Item getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}