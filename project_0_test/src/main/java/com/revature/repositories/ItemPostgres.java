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
			String i_name = rs.getString("i_name");
			double i_price = rs.getDouble("i_price");
			String i_offers_made = rs.getString("i_offers_made");
			int i_available = rs.getInt("i_available");
//			System.out.println(i_price);
			Item item =  new Item(i_name, i_price, i_offers_made, i_available, i_id);
			//item.setPrice(i_price);
			//item.setItemID(i_id);
			
			return item;
			
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
		String sql = "select * from Items where i_available > 0;";
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
	public Item add(Item item) {
		String sql = "insert into items (i_name, i_price, i_available) "
				+ "values (?, ?, ?) returning i_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, item.getName());
			ps.setDouble(2, item.getPrice());
			ps.setInt(3, item.getAvailable());


			int n = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				item.setItemID(rs.getInt(1));
			}
			
//			System.out.println("Recieve + " + item.getItemID());
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return item;
	}

	public boolean update(Item Items) {
		String sql = "update items set i_name = ?, i_price = ?, i_available = ? "
				+ "where i_id = ?;";

		int rowsChanged = -1;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, Items.getName());
			ps.setDouble(2, Items.getPrice());
			ps.setInt(3, Items.getAvailable());
			ps.setInt(4, Items.getItemID());

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
		String sql = "delete from items where i_id = ?;";
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

//	public static void main(String[] args) {
//		ItemPostgres itp = new ItemPostgres();
//		Item item = new Item("Car", 100);
//		item.setPrice(5);
//		
//		itp.add(item);
//		item.setPrice(10000);
//		itp.update(item);
//	}
}