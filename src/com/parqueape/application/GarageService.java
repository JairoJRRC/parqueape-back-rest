package com.parqueape.application;

import org.hibernate.Session;

import com.parqueape.domain.Garage;
import com.parqueape.infrastructure.HibernateUtil;

public class GarageService {
	
	public static Long create(Garage g) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(g);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + g.toString());
		return g.getId();
	}

}
