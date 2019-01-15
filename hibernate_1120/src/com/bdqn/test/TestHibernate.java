package com.bdqn.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bdqn.po.Emp;
import com.bdqn.util.HibernateUtil;

public class TestHibernate {
	Configuration config;
	SessionFactory factory;
	Session session;
	Transaction tr;
	public static void main(String[] args) {
		TestHibernate t = new TestHibernate();
		t.save();
	}
	public void delete(){
		session = HibernateUtil.getCurrentSession();
		tr = session.beginTransaction();
			//Emp emp =  (Emp)session.get(Emp.class,new Integer(7566));
			Emp emp = new Emp();  emp.setEmpNo(1459);
			session.delete(emp);
		tr.commit();
	}
	public void update(){
		config = new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		//开启事务，提交事务；提交事务默认关闭session
		session = factory.getCurrentSession();
		tr = session.beginTransaction();
				Emp emp = (Emp)session.get(Emp.class,new Integer(7566));
				//Emp emp2 = (Emp)session.get(Emp.class,new Integer(7654));
			//	emp.setEmpName("JONE2"); emp.setSal(2500);
				//不一致数据检查
				session.saveOrUpdate(emp);
		tr.commit();
	}
	//保存
	public void save(){
		config = new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		//开启事务，提交事务；提交事务默认关闭session
		session = factory.getCurrentSession();
		tr = session.beginTransaction();
			Emp emp = new Emp();
			//emp.setEmpName("hibe33");emp.setSal(2222);
			session.save(emp);
		tr.commit();//自动关闭session
			
	}
	//查询员工号=7566的员工信息
	public void  query(){
		//1. 读取配置文件
		config = new Configuration().configure("hibernate.cfg.xml");
		//2. 创建工厂
		factory = config.buildSessionFactory();
		//3. 打开session
		session = factory.openSession();
		//4. 查询
		Emp emp = (Emp)session.get(Emp.class,new Integer(7566));
		//持久状态---
		//session.clear(); //全部清空
		session.evict(emp);//清空指定一个
		//System.out.println(emp);
		Emp emp2 = (Emp)session.get(Emp.class,new Integer(7566));
		//System.out.println(emp2);
		//System.out.println(emp.getEmpName()+"---"+emp.getSal());
		session.close();
	}
}
