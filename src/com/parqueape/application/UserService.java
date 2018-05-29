package com.parqueape.application;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;
import com.parqueape.infrastructure.HibernateUtil;

public class UserService {

	public static Long create(User e) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();
	}

	public static List<User> read() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("FROM User").list();
		session.close();
		System.out.println("Found " + users.size() + " Users");
		return users;

	}

	public static void update(User e) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, e.getId());
		
		user.setIsActive(e.getIsActive());
		user.setPassword(e.getPassword());
		
		
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + e.toString());

	}

	public static void delete(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User e = findByID(id);
		e.setIsActive("0");
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully user inactived " + e.toString());

	}

	public static User findByID(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User e = (User) session.load(User.class, id);
		session.close();
		return e;
	}

	public static User findByEmail(String email, EnumRole role) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where email = :email and role = :role");
		query.setParameter("email", email);
		query.setParameter("role", role.toString());
		
		if(query.uniqueResult() == null) {
			throw new Exception("El Usuario no existe.");
		}
		
		User result = (User) query.uniqueResult();
		
		return result;
	}
	
	
	public static User findByEmailAndPassword(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where email = :email and password = :password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		User result = (User) query.uniqueResult();
		
		return result;
	}

}
