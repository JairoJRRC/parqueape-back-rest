package com.parqueape.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static{
		try{
			Configuration configuration = new Configuration()
					.setNamingStrategy(ImprovedNamingStrategy.INSTANCE)
					.configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			HibernateUtil.sessionFactory = configuration.buildSessionFactory(builder.build());

		}catch (Throwable ex) {
			System.err.println("Session Factory could not be created." + ex);
			throw new ExceptionInInitializerError(ex);
		}	
	}
	
	public static SessionFactory getSessionFactory() {
		return HibernateUtil.sessionFactory;
	}
	
}
