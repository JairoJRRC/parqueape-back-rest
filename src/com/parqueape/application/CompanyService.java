package com.parqueape.application;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;

import com.parqueape.domain.Company;
import com.parqueape.domain.User;
import com.parqueape.infrastructure.HibernateUtil;

public class CompanyService extends AuthenticationFactory{

	
	public static Long create(Company c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + c.toString());
		return c.getId();
	}

	public static List<Company> read() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Company> companies = session.createQuery("FROM Company").list();
		session.close();
		System.out.println("Found " + companies.size() + " Companies");
		return companies;

	}

	public static void update(Company company) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company c = (Company) session.load(Company.class, company.getId());
		
		c.setBusinessName(company.getBusinessName());
		c.setPhoneNumber(company.getPhoneNumber());
		c.setRuc(company.getRuc());
		c.setTradeName(company.getTradeName());
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + c.toString());
	}

	public static void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = findById(id);
		session.delete(company);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + company.toString());
	}

	public static Company findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company c = (Company) session.get(Company.class, id);
		session.close();
		return c;
	}
	
	public static Company findByUserId(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Company where userId = :id");
		query.setParameter("id", id);
		
		Company company= (Company) query.uniqueResult();
		session.close();
		return company;
	}

	@Override
	public JSONObject getDataUser(User user) {
		
		Company company = findByUserId(user.getId());
		
		return company.getObject();
	}

}
