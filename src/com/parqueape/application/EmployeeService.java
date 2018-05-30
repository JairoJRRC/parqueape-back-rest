package com.parqueape.application;

import org.hibernate.Session;

import com.parqueape.domain.Employee;
import com.parqueape.infrastructure.HibernateUtil;

public class EmployeeService {
	public static Long create(Employee e) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();
	}
}
