package com.revature.repositories;

import com.revature.models.Employee;

public interface EmployeeDao extends GenericDao<Employee> {

	Employee getByID(int id);

}