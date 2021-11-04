package com.revature.models;

public class User {
	
	private int id;
	private String name;
	private String username;
	private String password;
	private Role role;
	private User customer;
	private User employee;
	private User manager;
	
	
	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}


	public User(int id, String name, String username, String password, Role role, User customer, User employee,
			User manager) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.customer = customer;
		this.employee = employee;
		this.manager = manager;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public User getCustomer() {
		return customer;
	}


	public void setCustomer(User customer) {
		this.customer = customer;
	}


	public User getEmployee() {
		return employee;
	}


	public void setEmployee(User employee) {
		this.employee = employee;
	}


	public User getManager() {
		return manager;
	}


	public void setManager(User manager) {
		this.manager = manager;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}