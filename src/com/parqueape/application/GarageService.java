package com.parqueape.application;

import java.util.List;

import org.hibernate.Query;
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
	
	public static List<Garage> getGarageByCompany(Long companyId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("from Garage where company.id = :companyId");
		query.setParameter("companyId", companyId);
		List<Garage> garages = query.list();
		session.close();
		System.out.println("Encontrados: " + garages.size() + " Cocheras");
		return garages;

	}

	public static void update(Garage garage) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Garage g = (Garage) session.load(Garage.class, garage.getId());
		
		g.setTitle(garage.getTitle());
		g.setCoordinates(garage.getCoordinates());
		g.setAddress(garage.getAddress());
		g.setPhoto(garage.getPhoto());
		g.setCompany(garage.getCompany());
		g.setSites(garage.getSites());
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Actualizado correctamente : " + g.toString());
	}

	public static void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Garage garage = findById(id);
		session.delete(garage);
		session.getTransaction().commit();
		session.close();
		System.out.println("Eliminado correctamente " + garage.toString());
	}

	public static Garage findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Garage garage = (Garage) session.get(Garage.class, id);
		session.close();
		return garage;
	}

}
