package com.revature.repositories;

import java.util.List;
import com.revature.repositories.UserDao;
import com.revature.models.User;


public interface UserDao extends GenericDao<User> {
	List<User> getAllEmployee();
	User getEmployeeById(int id);
	User addEmployee(User employee);
	boolean updateEmployee(User employee);
}