package com.revature.daos;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeHibernate implements EmployeeDao{

	/**
	 * Adds an employee to the database
	 * @return the employee with the new id or null if no record is created
	 */
	@Override
	public Employee add(Employee e) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		
		int id = (int) s.save(e);
		tx.commit();
		
		return e;
	}

	/**
	 * Retrieves an employee by its id from the database
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		Employee e = null;
		
		//HQL
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			String hql = "from Employee where id = :e_id";
			TypedQuery<Employee> tq = s.createQuery(hql, Employee.class);
			tq.setParameter("e_id", id);
			try {e = tq.getSingleResult(); }
			catch (Exception err) { 				
			}
		}
		return e;
		
	
	}

	/**
	 * Retrieves an employee by its name from the database
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getByName(String name) {
		Employee e = null;
		//HQL
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			String hql = "from Employee where name = :name";
			TypedQuery<Employee> tq = s.createQuery(hql, Employee.class);
			tq.setParameter("name", name);
			e = tq.getSingleResult();
		}
		return e;
	}

	/**
	 * Retrieves all employees from the database
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			emps = s.createNamedQuery("getAllEmps", Employee.class).getResultList();
		}
		
		return emps;
	}

	/**
	 * Retrieves employees by department id from the database
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployeesByDepartmentId(int deptId) {
		List<Employee> e = null;
		
		//HQL
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			String hql = "from Employee where d_id = :d_id";
			TypedQuery<Employee> tq = s.createQuery(hql, Employee.class);
			tq.setParameter("d_id", deptId);
			e = tq.getResultList();
		}
		return e;
	}

	/**
	 * Updates an employee
	 */
	@Override
	public void update(Employee d) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.update(d);
			tx.commit();
		}
		
	}
	
	/**
	 * Deletes an employee
	 */
	@Override
	public void delete(Employee d) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(d);
			tx.commit();
		}

		
	}
}