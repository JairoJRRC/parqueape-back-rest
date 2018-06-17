package com.parqueape.application;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;

import com.parqueape.domain.Employee;
import com.parqueape.domain.User;
import com.parqueape.infrastructure.HibernateUtil;

public class EmployeeService extends AuthenticationFactory {
	public static Long create(Employee e) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();
	}
	
	public static List<Employee> read() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Employee> emp = session.createQuery("FROM Employee").list();
		session.close();
		System.out.println("Found " + emp.size() + " Employee");
		return emp;

	}

	public static void update(Employee emp) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee c = (Employee) session.load(Employee.class, emp.getId());
		
		c.setDateEntry(emp.getDateEntry());
		c.setSalary(emp.getSalary());
		c.setDateRetirement(emp.getDateRetirement());
		c.setState(emp.getState());
		c.setTurn(emp.getTurn());
		c.setBankAccountNumber(emp.getBankAccountNumber());
		c.setNames(emp.getNames());
		c.setLastNames(emp.getLastNames());
		c.setTypeDoc(emp.getTypeDoc());
		c.setNumDoc(emp.getNumDoc());
		c.setPhoto(emp.getPhoto());
		c.setCompany(emp.getCompany());
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + c.toString());
	}

	public static void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee employee = findById(id);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + employee.toString());
	}

	public static Employee findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Employee c = (Employee) session.load(Employee.class, id);
		session.close();
		return c;
	}
	
	public static Employee findByUserId(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Employee where userId = :id");
		query.setParameter("id", id);
		
		Employee employee= (Employee) query.uniqueResult();
		
		return employee;
	}

	@Override
	public JSONObject getDataUser(User user) {
		Employee emp = findByUserId(user.getId());
		return emp.getObject();
	}

	
}
