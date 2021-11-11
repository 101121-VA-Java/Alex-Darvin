//package com.revature.repositories;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.revature.models.Cart;
//import com.revature.util.ConnectionUtil;
//
//public class CartPostgres implements CartDao {
//
//	private Cart makeNewCart(ResultSet rs) {
//
//		try {
//			int ct_id = rs.getInt("ct_id");
//			String ct_item_name = rs.getString("ct_item_name");
//			double ct_item_price = rs.getDouble("ct_item_price");
//			String ct_offers_made = rs.getString("ct_offers_made");
//			int ct_in_cart = rs.getInt("ct_in_cart");
//			int ct_customer_id = rs.getInt("ct_customer_id");
////			System.out.println(i_price);
//			Cart cart =  new Cart(ct_id, ct_item_name, ct_item_price, ct_offers_made, ct_in_cart, ct_customer_id);
//			//item.setPrice(i_price);
//			//item.setCartID(i_id);
//			
//			return cart;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	@Override
//	public Cart getByID(int id) {
//		String sql = "select * from Carts where ct_id = ? ";
//		Cart ct = null;
//
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				return makeNewCart(rs);
//			}
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		return ct;
//	}
//
//	@Override
//	public List<Cart> getAll() {
//		String sql = "select * from Carts;";
//		List<Cart> Carts = new ArrayList<>();
//
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			Statement s = con.createStatement();
//			ResultSet rs = s.executeQuery(sql);
//
//			while (rs.next()) {
//				Carts.add(makeNewCart(rs));
//				
//			}
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		return Carts;
//	}
//
//	@Override
//	public List<Cart> getInCart() {
//		String sql = "select * from Carts where i_in_cart > 0;";
//		List<Cart> Carts = new ArrayList<>();
//
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			Statement s = con.createStatement();
//			ResultSet rs = s.executeQuery(sql);
//
//			while (rs.next()) {
//
//				Carts.add(makeNewCart(rs));
//			}
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		return Carts;
//
//	}
//
//	@Override
//	public Cart add(Cart item) {
//		String sql = "insert into carts (i_name, i_price, i_available) "
//				+ "values (?, ?, ?) returning i_id;";
//
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//			ps.setString(1, item.getItemName());
//			ps.setDouble(2, item.getItemPrice());
//			ps.setInt(3, item.getInCart());
//
//
//			int n = ps.executeUpdate();
//			ResultSet rs = ps.getGeneratedKeys();
//			if(rs.next()) {
//				item.setCartID(rs.getInt(1));
//			}
//			
////			System.out.println("Recieve + " + item.getCartID());
//			
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		return item;
//	}
//
//	public boolean update(Cart Carts) {
//		String sql = "update carts set i_name = ?, i_price = ?, i_available = ? "
//				+ "where i_id = ?;";
//
//		int rowsChanged = -1;
//
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			PreparedStatement ps = con.prepareStatement(sql);
//
//			ps.setString(1, Carts.getItemName());
//			ps.setDouble(2, Carts.getItemPrice());
//			ps.setInt(3, Carts.getInCart());
//			ps.setInt(4, Carts.getCartID());
//
//			rowsChanged = ps.executeUpdate();
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		if (rowsChanged > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public Cart remove(Cart Carts) {
//		String sql = "delete from carts where i_id = ?;";
//		int rowsChanged = -1;
//		int id = Carts.getCartID();
//		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			rowsChanged = ps.executeUpdate();
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		if (rowsChanged > 0) {
//			return null;
//		} else {
//			return Carts;
//		}
//
//	}
//
//	@Override
//	public Cart getByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}