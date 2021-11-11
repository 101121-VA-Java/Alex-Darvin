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

import com.revature.models.Employee;
import com.revature.models.Offer;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDao {
	
	

	@Override
	public Offer add(Offer u) {
		// TODO Auto-generated method stub
		String sql = "insert into offers (customerId, itemId, amount, accepted)" + "values (?, ?, ?, ?) returning offerId"; 
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, u.getCustomerId());
			ps.setInt(2, u.getItemId());
			ps.setFloat(3, u.getAmount());
			ps.setBoolean(4, u.isOfferAccepted());


			int n = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				u.setOfferId(rs.getInt(1));
			}
			
//			System.out.println("Recieve + " + offer.getItemID());
			
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
		String sql = "select * from offers;";
		List<Offer> offers = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int offerId = rs.getInt("offerId");
				int customerId = rs.getInt("customerId");
				int itemId = rs.getInt("itemId");
				float amount = rs.getFloat("amount");
				boolean accepted = rs.getBoolean("accepted");
				

			Offer newOffer = new Offer(offerId, customerId, itemId, amount, accepted);
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
		List<Offer> offers = new ArrayList<>();

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
		return result;
	}

	@Override
	public Offer remove(Offer u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}