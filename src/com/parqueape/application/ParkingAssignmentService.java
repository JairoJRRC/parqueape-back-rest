package com.parqueape.application;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.parqueape.domain.ParkingAssignment;
import com.parqueape.infrastructure.HibernateUtil;

public class ParkingAssignmentService {
	
	public static Long create(ParkingAssignment c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		return c.getId();
	}

	public static void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ParkingAssignment parkingAssignment = findById(id);
		session.delete(parkingAssignment);
		session.getTransaction().commit();
		session.close();
	}

	public static ParkingAssignment findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ParkingAssignment c = (ParkingAssignment) session.get(ParkingAssignment.class, id);
		session.close();
		return c;
	}
	
	public static List<ParkingAssignment> findByEmployeId(Long employeeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("from ParkingAssignment where employeeId = :employeeId");
		query.setParameter("employeeId", employeeId);
		List<ParkingAssignment> list = query.list();
		session.close();
		System.out.println("Encontrados: " + list.size() + " ParkingAssignment");
		return list;

	}
}
