package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Roles;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDao{

	@Override
	public User add(User u) {
		User newGuy = u;
		String sql = "insert into ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) values (?, ?, ?, ?, ?, ?) returning ERS_USERS_ID;";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getUserId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				newGuy.setUserId(rs.getInt("ERS_USERS_ID"));
			}
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return newGuy;

	}

	@Override
	public List<User> getAll() {
		String sql = "select * from ERS_USERS;";
		List<User> Users = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("USER_ROLE_ID");
				String username = rs.getString("ERS_USERNAME");
				String password = rs.getString("ERS_PASSWORD");
				String firstName = rs.getString("USER_FIRST_NAME");
				String lastName = rs.getString("USER_LAST_NAME");
				String email = rs.getString("USER_EMAIL");
				int roleId = rs.getInt("USER_ROLE_ID");
				
				
				User newUser = new User(id, username, password, firstName, lastName, email, new Role(roleId));
				Users.add(newUser);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return Users;
	}

	@Override
	public User getById(int id) {
		String sql = "select * from ERS_USERS where USER_ROLE_ID = ?;";
		User use = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int userId = rs.getInt("USER_ROLE_ID");
				String username = rs.getString("ERS_USERNAME");
				String password = rs.getString("ERS_PASSWORD");
				String firstName = rs.getString("USER_FIRST_NAME");
				String lastName = rs.getString("USER_LAST_NAME");
				String email = rs.getString("USER_EMAIL");
				int roleId = rs.getInt("USER_ROLE_ID");
				
				use = new User(userId, username, password, firstName, lastName, email, new Role(roleId));
				
			}
		} 
		catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return use;
	}

	@Override
	public boolean remove(User u) {
		int rs = -1;
		String sql = "delete from ERS_USERS where USER_ROLE_ID = ?;";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, u.getUserId());
			rs = ps.executeUpdate();
			
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rs > 0) {
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean update(User u) {
		int rs = -1;
		String sql = "update ERS_USERS set roleId where USER_ROLE_ID = ?;";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, u.getUserId());
			rs = ps.executeUpdate();
			
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (rs > 0) {
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean loginUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User viewUserInfo(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserInfo(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User viewAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}