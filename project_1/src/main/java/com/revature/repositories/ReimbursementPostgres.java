package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementPostgres implements ReimbursementDao{

	public List<Reimbursement> getAll() {
		String sql = "select * from ERS_REIMBURSEMENTS;";
		List<Reimbursement> Reimbs = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("REIMB_ID");
				double amount = rs.getDouble("REIMB_AMOUNT");
				Timestamp submitted = rs.getTimestamp("REIMB_SUBMITTED");
				Timestamp resolved = rs.getTimestamp("REIMB_RESOLVED");
				String descrip = rs.getString("REIMB_DESCRIPTION");
				int author = rs.getInt("REIMB_AUTHOR");
				int resolver = rs.getInt("REIMB_RESOLVER");
				int statusId = rs.getInt("REIMB_STATUS_ID");
				int typeId = rs.getInt("REMI_TYPE_ID");
				
				
				Reimbursement newReib = new Reimbursement(id, amount, submitted, resolved, descrip, new User(author), new User(resolver), new Status(statusId), new Type(typeId));
				Reimbs.add(newReib);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return Reimbs;
	}


	public Reimbursement getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean remove(Reimbursement o) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Reimbursement o) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Reimbursement> getAllByUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean checkApproved(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean updateApproval(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean submitReimbRequest(Reimbursement r) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Reimbursement viewPendingReimb(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Reimbursement viewResolvedReimb(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Reimbursement viewPendingRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean updateRequest(Reimbursement r) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Reimbursement viewResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Reimbursement viewRequest(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement addReimb(Reimbursement reimb) {
		// TODO Auto-generated method stub
		return null;
	}
	
}