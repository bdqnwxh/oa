package com.bdqn.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bdqn.po.Dept;
import com.bdqn.po.Emp;
import com.bdqn.pojo.Employee;
import com.bdqn.util.HibernateSessionFactory;

public class TestHibernate {
	public static void main(String[] args) {
		TestHibernate.queryEmp3();
	}
	//��ѯԱ����7566
	public static void queryEmp3(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			//Employee  emp = (Employee)session.createQuery("from Employee where empNo=7566").uniqueResult();
		//	System.out.println(emp.getEname()+"---"+emp.getDept().getDname());
			tr.commit();
	}
	
	//
	
	//��ѯ���ź�Ϊ30�Ĳ�����Ϣ����ʾ����Ա��
	public static void queryDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			Dept  d = (Dept)session.get(Dept.class, new Long(30));
			System.out.println(d.getDname());
			for(Emp e :d.getEmps()){
				System.out.println(e.getEname());
			}
		tr.commit();
	}
	
	//��ӣ����һ�����ţ�ͬʱ���˲����������Ա����ֻ���沿�ţ����Ա��
	public static void saveDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
				Dept d = new Dept();  d.setDname("�з���");
				//1. Ա�����в���
				Emp emp1 = new Emp(); emp1.setEname("xiaom"); emp1.setDept(d);
				Emp emp2 = new Emp(); emp2.setEname("xiaom2");emp2.setDept(d);
				//2.��������Ա������
				d.getEmps().add(emp1);
				d.getEmps().add(emp2);
				//3. ���沿�ţ�ͬʱ����Ա�� �� cascade="save-update"
				session.save(d);
				
		tr.commit();
	}
	
	//��ѯ��������=SALES��Ա���б� ��   from Emp  e  where   e.dept.dname='sales'
	public static void  queryEmp2(){
		Session session = HibernateSessionFactory.getSession();
			List<Dept> emps = session.createQuery("from Dept  d  where  5000>(select max(e.sal) from d.emps e)").list();
			//��ѯԱ�����ڲ���
			System.out.println(emps);
		session.close();
	}
	//��ѯԱ����=7566�Ĳ�������
	public static void  queryEmp(){
		Session session = HibernateSessionFactory.getSession();
			Emp emp = (Emp)session.get(Emp.class, new Long(7566));
			//��ѯԱ�����ڲ���
			System.out.println(emp.getEname()+"---"+emp.getDept().getDname());
		session.close();
	}
}
