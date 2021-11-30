package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.repositories.UserDao;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDao {

	public UserPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAll() {
		List<User> ers_users = new ArrayList<>();
		String sql = "select * from ers_users full join ers_user_roles on user_role_id = ers_user_roles.ers_user_role_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int ers_users_id = rs.getInt("ers_users_id");
				String user_first_name = rs.getString("user_first_name");
				String user_last_name = rs.getString("user_last_name");
				String ers_username = rs.getString("ers_username");
				String ers_password = rs.getString("ers_password");
				String user_email = rs.getString("user_email");
				int user_role_id = rs.getInt("user_role_id");
				String user_role = rs.getString("user_role");

				User newUser = new User(ers_users_id, user_first_name, user_last_name, ers_username, ers_password,
						user_email, new Role(user_role_id, user_role)); // need to add role
				ers_users.add(newUser);
			}
		} catch (SQLException | IOException c) {
			c.printStackTrace();
		}
		return ers_users;
	}

	@Override
	public User getById(int id) {
		String sql = "select * from ers_users full join ers_user_roles on user_role_id = ers_user_roles.ers_user_role_id where ers_users_id = ?;";
		User use = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ers_users_id = rs.getInt("ers_users_id");
				String user_first_name = rs.getString("user_first_name");
				String user_last_name = rs.getString("user_last_name");
				String ers_username = rs.getString("ers_username");
				String ers_password = rs.getString("ers_password");
				String user_email = rs.getString("user_email");
				int user_role_id = rs.getInt("user_role_id");
				String user_role = rs.getString("user_role");

				use = new User(ers_users_id, user_first_name, user_last_name, ers_username, ers_password, user_email,
						new Role(user_role_id, user_role)); // add role
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return use;
	}
	
	public User getByUsername(String username) {
		String sql = "select * from ers_users full join ers_user_roles on user_role_id = ers_user_roles.ers_user_role_id where ers_username = ?;";
		User use = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ers_users_id = rs.getInt("ers_users_id");
				String user_first_name = rs.getString("user_first_name");
				String user_last_name = rs.getString("user_last_name");
				String ers_username = rs.getString("ers_username");
				String ers_password = rs.getString("ers_password");
				String user_email = rs.getString("user_email");
				int user_role_id = rs.getInt("user_role_id");
				String user_role = rs.getString("user_role");

				use = new User(ers_users_id, user_first_name, user_last_name, ers_username, ers_password, user_email,
						new Role(user_role_id, user_role));
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return use;
	}
	

	@Override
	public int add(User u) {
		int genId = -1;
		String sql = "insert into ers_users (user_first_name, user_last_name, ers_username, ers_password, user_email, user_role_id)"
				+ "values (?, ?, ?, ?, ?, ?) returning ers_users_id;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRole().getRoleId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				genId = rs.getInt("ers_users_id");
			}

		} catch (SQLException | IOException c1) {
			// TODO Auto-generated catch block
			c1.printStackTrace();
		}
		return genId;
	}

	@Override
	public boolean edit(User u) {
		String sql = "update ers_users set user_first_name = ?, user_last_name = ?, ers_username = ?,"
				+ " ers_password = ?, user_email = ?, user_role_id = ? where ers_users_id = ?;"; // add role
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1;
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRole().getRoleId());
			ps.setInt(7, u.getId());

			rowsChanged = ps.executeUpdate();

			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c1) {
			c1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "delete from ers_users where ers_users_id = ?;";
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return false;
	}


	@Override
	public User getUserById(int token_id) {
		// TODO Auto-generated method stub
		return null;
	}
}