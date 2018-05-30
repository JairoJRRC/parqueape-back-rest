package com.parqueape.application;

import org.hibernate.Session;

import com.parqueape.domain.Site;
import com.parqueape.infrastructure.HibernateUtil;

public class SiteService {
	public static Long create(Site s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + s.toString());
		return s.getId();
	}
}
