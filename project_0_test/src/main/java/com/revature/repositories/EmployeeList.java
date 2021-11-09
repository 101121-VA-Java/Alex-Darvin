package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;

public class EmployeeList implements EmployeeDao {

	private List<Employee> employees;
	
	public EmployeeList() {
		employees = new ArrayList<>();
		
	}
	
	@Override
	public Employee add(Employee u) {
		u.setEmployeeID(employees.size());
		employees.add(u);
		return u;
	}
	
	@Override
	public Employee getByID(int id) {
		for(Employee e: employees) {
			if(e.getEmployeeID() == id) {
				return e;
			}	
		}
		return null;
	}
	
	@Override
	public List<Employee> getAll(){
		return employees;
	}
	
	@Override
	public boolean update(Employee u) {
		Employee temp = getByID(u.getEmployeeID());
		if(temp == null || temp.equals(u)) {
			return false;
		}
		employees.set(u.getEmployeeID(), u);
		return true;
	}
	
	@Override
	public Employee remove(Employee u) {
		employees.remove(u);
		return u;
	}

	@Override
	public Employee getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}