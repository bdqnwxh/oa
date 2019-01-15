package com.bdqn.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration config;
	private static SessionFactory factory;
	
	static{
		config = new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
	}
	
	public  static Session getCurrentSession(){
		return factory.getCurrentSession();
	}
	
}
