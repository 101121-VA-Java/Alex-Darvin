package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		//need to get author username from User table, need a join table statement
		
		String sql = "select * from ERS_REIMBURSEMENTS;";
		List<Reimbursement> Reimbs = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int statusId = rs.getInt("reimb_status_id");
				int typeId = rs.getInt("reimb_type_id");
				
				
				Reimbursement newReimb = new Reimbursement(id, amount, submitted, resolved, description, new User(author), new User(resolver), new Status(statusId, description), new Type(typeId, description));
				Reimbs.add(newReimb);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return Reimbs;
	}


	public Reimbursement getById(int id) {
		// TODO Auto-generated method stub
		String sql = "select a.*, u.ers_username as authname, u1.ers_username as resolvername, s.reimb_status, t.reimb_type from ers_reimbursement as a "
				+ "left join ers_users as u on u.ers_users_id = a.reimb_author "
				+ "left join ers_users as u1 on u1.ers_users_id = a.reimb_resolver "
				+ "left join ers_reimbursement_status as s on s.reimb_status_id = a.reimb_status_id "
				+ "left join ers_reimbursement_type as t on t.reimb_type_id = a.reimb_type_id "
				+ "where a.reimb_id = ?;";
		Reimbursement model = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getDouble("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				int reimb_author = rs.getInt("reimb_author");
				String authname = rs.getString("authname");
				int reimb_resolver = rs.getInt("reimb_resolver");
				String resolvername = rs.getString("resolvername");
				int reimb_status_id = rs.getInt("reimb_status_id");
				String reimb_status = rs.getString("reimb_status");
				int reimb_type_id = rs.getInt("reimb_type_id");
				String reimb_type = rs.getString("reimb_type");
				
				User author = new User(reimb_author, authname);
				User resolver = new User(reimb_resolver, resolvername);
				Status status = new Status(reimb_status_id, reimb_status);

				model = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description,
						author, resolver, status, new Type(reimb_type_id, reimb_type)); // 
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return model;
	}


	public boolean remove(Reimbursement o) {
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
	public int add(Reimbursement t) {
		// TODO Auto-generated method stub
		int genId = -1;
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) returning reimb_id;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDouble(1, t.getReimAmount());
			ps.setTimestamp(2, t.getSubmit());
			ps.setTimestamp(3, t.getResolve());
			ps.setString(4, t.getDescrip());
			ps.setInt(5, t.getAuthor() != null ? t.getAuthor().getId() : 1);
			ps.setInt(6, t.getResolver() != null ? t.getResolver().getId() : 1);
			ps.setInt(7, t.getStatus() != null ? t.getStatus().getId() : 1);
			ps.setInt(8, t.getType() != null ? t.getType().getId() : 1);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				genId = rs.getInt("reimb_id");
			}

		} catch (SQLException | IOException c1) {
			// TODO Auto-generated catch block
			c1.printStackTrace();
		}
		return genId;
	}


	@Override
	public List<Reimbursement> getByStatusId(int id) {
		System.out.println("status_id: " + id);
		// TODO Auto-generated method stub
		String sql = "select a.*, u.ers_username as authname, u1.ers_username as resolvername, s.reimb_status, t.reimb_type from ers_reimbursement as a "
				+ "left join ers_users as u on u.ers_users_id = a.reimb_author "
				+ "left join ers_users as u1 on u1.ers_users_id = a.reimb_resolver "
				+ "left join ers_reimbursement_status as s on s.reimb_status_id = a.reimb_status_id "
				+ "left join ers_reimbursement_type as t on t.reimb_type_id = a.reimb_type_id "
				+ "where a.reimb_status_id = ?;";
		List<Reimbursement> r = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getDouble("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				int reimb_author = rs.getInt("reimb_author");
				String authname = rs.getString("authname");
				int reimb_resolver = rs.getInt("reimb_resolver");
				String resolvername = rs.getString("resolvername");
				int reimb_status_id = rs.getInt("reimb_status_id");
				String reimb_status = rs.getString("reimb_status");
				int reimb_type_id = rs.getInt("reimb_type_id");
				String reimb_type = rs.getString("reimb_type");
				
				
				User author = new User(reimb_author, authname);
				User resolver = new User(reimb_resolver, resolvername);
				Status status = new Status(reimb_status_id, reimb_status);

				Reimbursement model = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description,
						author, resolver, status, new Type(reimb_type_id, reimb_type)); // add role
				
				r.add(model);
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return r;
	}


	@Override
	public List<Reimbursement> getByAuthorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean edit(Reimbursement t) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}