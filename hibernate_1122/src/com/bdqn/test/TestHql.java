package com.bdqn.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bdqn.po.Dept;
import com.bdqn.po.Emp;
import com.bdqn.pojo.Department;
import com.bdqn.util.HibernateSessionFactory;

public class TestHql {
	public static void main(String[] args) {
		TestHql.queryDept2();
	}
	public static void queryDept2(){
		Session session  = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			List<Department> dlist = session.createQuery("from Department").list();
			for (Department d : dlist) {
				System.out.println(d.getDname()+"---"+d.getLoc());
			}
		tr.commit();
	}
	/*
	 * select * from  dept d  where d.deptno  in
(select e.deptno  from emp e
 group by e.deptno
having  max(e.sal)<5000)
	 查询所有员工的工资都小于5000 的部门号，部门名
	 */
	public static void queryDeptSalMax(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			//String hql = "from  Dept d   where   (select max(e.sal)  from d.emps e)<5000";
		String hql = "from  Dept d   where  d.emps.size >0  and  "
				+ "5000< any(select e.sal  from d.emps e)  ";
			
		List<Dept> list = session.createQuery(hql).list();
			for(Dept dept: list){ 
				System.out.println(dept.getDname());
			}
			tr.commit();
	}
	
	//统计各个部门员工数，工资最大值 , 显示员工数大于1的信息
	public static void queryEmpCount(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "select  e.dept.deptno,count(*),max(e.sal) from Emp e  "
				+ "group by  e.dept.deptno  having  count(*)>1";
		List<Object[]> objs =  session.createQuery(hql).list();
		for(Object[] count : objs){
			System.out.println(count[0]+"---"+count[1]+"---"+count[2]);
		}
		tr.commit();
	}
	
	//隐式内连接： 查询部门名称=人事部的员工信息
	public static void queryEmp2(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "from  Emp e  where e.dept.dname='人事部'";
		List<Emp>  emplist = session.createQuery(hql).list();
		for(Emp e : emplist){
			System.out.println("---"+e.getEname()+"---"+e.getDept().getDname());
		}
		tr.commit();
	}
	
	//查询员工信息，展示部门信息
	public static void queryEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "from Emp  e  inner join e.dept";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object obj[] : list){
			Emp emp = (Emp) obj[0];
			System.out.println(emp.getEname());
			Dept dept = (Dept)obj[1];
			System.out.println("-----"+dept.getDname());
		}
		tr.commit();
	}
	//查询部门列表以及部门的员工信息
		public static void queryDeptFetch(){
			Session session = HibernateSessionFactory.getSession();
			Transaction tr = session.beginTransaction();
			//去掉重复
			String hql = "select  distinct  d  from Dept  d  inner join fetch  d.emps";
			List<Dept> list = session.createQuery(hql).list();
			for(Dept dept: list){ 
				System.out.println(dept.getDname()+"---");
				List<Emp> emplist = dept.getEmps();
				for(Emp e : emplist){
					System.out.println("---"+e.getEname());
				}
			}
			tr.commit();
		}
	//查询部门列表以及部门的员工信息
	public static void queryDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "from Dept  d  inner join   d.emps";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object obj[] : list){
			Dept dept = (Dept)obj[0];//部门对象
			System.out.println(dept.getDname()+"---");
		/*	Emp emp = (Emp)obj[1];
			System.out.println("---"+emp.getEname());*/
		}
		tr.commit();
	}
}
