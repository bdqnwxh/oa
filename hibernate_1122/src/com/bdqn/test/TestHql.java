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
	 ��ѯ����Ա���Ĺ��ʶ�С��5000 �Ĳ��źţ�������
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
	
	//ͳ�Ƹ�������Ա�������������ֵ , ��ʾԱ��������1����Ϣ
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
	
	//��ʽ�����ӣ� ��ѯ��������=���²���Ա����Ϣ
	public static void queryEmp2(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "from  Emp e  where e.dept.dname='���²�'";
		List<Emp>  emplist = session.createQuery(hql).list();
		for(Emp e : emplist){
			System.out.println("---"+e.getEname()+"---"+e.getDept().getDname());
		}
		tr.commit();
	}
	
	//��ѯԱ����Ϣ��չʾ������Ϣ
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
	//��ѯ�����б��Լ����ŵ�Ա����Ϣ
		public static void queryDeptFetch(){
			Session session = HibernateSessionFactory.getSession();
			Transaction tr = session.beginTransaction();
			//ȥ���ظ�
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
	//��ѯ�����б��Լ����ŵ�Ա����Ϣ
	public static void queryDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		String hql = "from Dept  d  inner join   d.emps";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object obj[] : list){
			Dept dept = (Dept)obj[0];//���Ŷ���
			System.out.println(dept.getDname()+"---");
		/*	Emp emp = (Emp)obj[1];
			System.out.println("---"+emp.getEname());*/
		}
		tr.commit();
	}
}
