package com.revature.repositories;

import java.util.List;
import com.revature.models.User;


public interface UserDao {
	List<User> getAllEmployee();
	User getEmployeeById(int id);
	User addEmployee(User employee);
	boolean updateEmployee(User employee);
}