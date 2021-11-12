package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.models.Offer;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDao {
	
	

	@Override
	public Offer add(Offer u) {
		// TODO Auto-generated method stub
		String sql = "insert into offers (customerEmail, itemId, amount, accepted)" + "values (?, ?, ?, ?) returning offerId"; 
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, u.getCustomerEmail());
			ps.setInt(2, u.getItemId());
			ps.setFloat(3, u.getAmount());
			ps.setBoolean(4, u.isOfferAccepted());


			int n = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				u.setOfferId(rs.getInt(1));
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Offer getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from offers;";// where customerEmail = ?
		List<Offer> offers = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int offerId = rs.getInt("offerId");
				String customerEmail = rs.getString("customerEmail");
				int itemId = rs.getInt("itemId");
				float amount = rs.getFloat("amount");
				boolean accepted = rs.getBoolean("accepted");
				

			Offer newOffer = new Offer(offerId, itemId, customerEmail, amount, accepted);
			offers.add(newOffer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return offers;
	}

	@Override
	public boolean update(Offer u) {
		
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Which offer would you like to change? Enter ID:");
		int offerId = sc.nextInt();
		System.out.println("What is the Item associated with the Offer? Enter Item ID:");
		int itemId = sc.nextInt();
		System.out.println("Pending Offer Approval...");
		
		String sql = "select * from offers; update offers set accepted = ? where offerId = ?;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			ps.setBoolean(1, true);
			ps.setInt(2, offerId);
			ps.execute();
			
			result = true;
			String sqlOtherOffers = "delete from offers where itemId = ? and accepted = false";
			PreparedStatement psOther = con.prepareStatement(sqlOtherOffers);
			
			psOther.setInt(1, itemId);
			psOther.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sc.close();
		return result;
		
	}
	
	public static boolean updateById(int id, int itemId) {
			
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		String sql = "update offers set accepted = true where offerId = ?";
		int rs = -1;
		int rs2 = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			rs = ps.executeUpdate();
			
//			result = true;
			String sqlOtherOffers = "delete from offers where itemId = ? and accepted = false";
			PreparedStatement psOther = con.prepareStatement(sqlOtherOffers);
			
			psOther.setInt(1, itemId);
			
			rs2 = psOther.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rs > 0 && rs2 > 0) {
			result = true;
		} else {
			result = false;
		}
			
		return result;
		
	}

	public static boolean removeById(int id) {
		
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		String sql = "delete from offers where offerId = ?";
		int rs = -1;

		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			rs = ps.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rs > 0 ) {
			result = true;
		} else {
			result = false;
		}
			
		return result;
		
	}
	@Override
	public Offer remove(Offer u) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Offer getByID(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from offers where offerid = ?;";// where customerEmail = ?
		Offer offer = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int offerId = rs.getInt("offerId");
				String customerEmail = rs.getString("customerEmail");
				int itemId = rs.getInt("itemId");
				float amount = rs.getFloat("amount");
				boolean accepted = rs.getBoolean("accepted");
				
				offer = new Offer(offerId, itemId, customerEmail, amount, accepted);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return offer;
	}
	
}