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
		//���������ύ�����ύ����Ĭ�Ϲر�session
		session = factory.getCurrentSession();
		tr = session.beginTransaction();
				Emp emp = (Emp)session.get(Emp.class,new Integer(7566));
				//Emp emp2 = (Emp)session.get(Emp.class,new Integer(7654));
			//	emp.setEmpName("JONE2"); emp.setSal(2500);
				//��һ�����ݼ��
				session.saveOrUpdate(emp);
		tr.commit();
	}
	//����
	public void save(){
		config = new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		//���������ύ�����ύ����Ĭ�Ϲر�session
		session = factory.getCurrentSession();
		tr = session.beginTransaction();
			Emp emp = new Emp();
			//emp.setEmpName("hibe33");emp.setSal(2222);
			session.save(emp);
		tr.commit();//�Զ��ر�session
			
	}
	//��ѯԱ����=7566��Ա����Ϣ
	public void  query(){
		//1. ��ȡ�����ļ�
		config = new Configuration().configure("hibernate.cfg.xml");
		//2. ��������
		factory = config.buildSessionFactory();
		//3. ��session
		session = factory.openSession();
		//4. ��ѯ
		Emp emp = (Emp)session.get(Emp.class,new Integer(7566));
		//�־�״̬---
		//session.clear(); //ȫ�����
		session.evict(emp);//���ָ��һ��
		//System.out.println(emp);
		Emp emp2 = (Emp)session.get(Emp.class,new Integer(7566));
		//System.out.println(emp2);
		//System.out.println(emp.getEmpName()+"---"+emp.getSal());
		session.close();
	}
}
