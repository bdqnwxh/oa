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
	//查询员工号7566
	public static void queryEmp3(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			//Employee  emp = (Employee)session.createQuery("from Employee where empNo=7566").uniqueResult();
		//	System.out.println(emp.getEname()+"---"+emp.getDept().getDname());
			tr.commit();
	}
	
	//
	
	//查询部门号为30的部门信息，显示所有员工
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
	
	//添加：添加一个部门，同时给此部门添加两个员工。只保存部门，添加员工
	public static void saveDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
				Dept d = new Dept();  d.setDname("研发部");
				//1. 员工中有部门
				Emp emp1 = new Emp(); emp1.setEname("xiaom"); emp1.setDept(d);
				Emp emp2 = new Emp(); emp2.setEname("xiaom2");emp2.setDept(d);
				//2.部门中有员工集合
				d.getEmps().add(emp1);
				d.getEmps().add(emp2);
				//3. 保存部门，同时保存员工 ， cascade="save-update"
				session.save(d);
				
		tr.commit();
	}
	
	//查询部门名称=SALES的员工列表 ：   from Emp  e  where   e.dept.dname='sales'
	public static void  queryEmp2(){
		Session session = HibernateSessionFactory.getSession();
			List<Dept> emps = session.createQuery("from Dept  d  where  5000>(select max(e.sal) from d.emps e)").list();
			//查询员工所在部门
			System.out.println(emps);
		session.close();
	}
	//查询员工号=7566的部门名称
	public static void  queryEmp(){
		Session session = HibernateSessionFactory.getSession();
			Emp emp = (Emp)session.get(Emp.class, new Long(7566));
			//查询员工所在部门
			System.out.println(emp.getEname()+"---"+emp.getDept().getDname());
		session.close();
	}
}
