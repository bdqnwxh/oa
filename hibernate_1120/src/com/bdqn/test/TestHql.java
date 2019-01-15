package com.bdqn.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bdqn.po.Emp;
import com.bdqn.util.HibernateUtil;

public class TestHql {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println("初始化64M："+Runtime.getRuntime().totalMemory());
		byte b[] = new byte[600*1024*1024]; 
		System.out.println("----------");
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println("初始化64M："+Runtime.getRuntime().totalMemory());
		System.out.println("----------");
	
	}
	//投影查询： 一列 : Object  ，二列，多列： Object[];  返回对象类型
	public void  queryColumns(){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String hql = "select  new Emp(empName,job)  from Emp";
		Query query = session.createQuery(hql);
		List<Emp>  elist = query.list();
		for(Emp e :elist) {
			System.out.println(e.getEmpName()+"--"+e.getJob());
		}	
		/*String hql = "select empName,job  from Emp";
		Query query = session.createQuery(hql);
		List<Object[]> emplist = query.list();
		for(Object str[] : emplist){
			System.out.println(str[0]+"---"+str[1]);
		}*/
		tr.commit();
	}
	
	//总记录
	public void queryCount(Emp emp){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		StringBuffer  sql = new StringBuffer();
		sql.append("select count(*) from Emp where 1=1 ");
		if(emp.getSal()!=null){
			sql.append("  and  sal > :sal");
		}
		if(emp.getEmpName()!=null && !"".equals(emp.getEmpName())){
			sql.append("  and  empName like :empName");
		}
		Query query = session.createQuery(sql.toString());
		query.setProperties(emp);
		//唯一一条结果
		Object obj = query.uniqueResult();
		long  count = (Long)obj;
		System.out.println(count);
		tr.commit();
	}
	//动态sql
	public void queryHqlSql(Emp emp){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		StringBuffer  sql = new StringBuffer();
		sql.append("from Emp where 1=1 ");
		if(emp.getSal()!=null){
			sql.append("  and  sal > :sal");
		}
		if(emp.getEmpName()!=null && !"".equals(emp.getEmpName())){
			sql.append("  and  empName like :empName");
		}
		Query query = session.createQuery(sql.toString());
		query.setProperties(emp); //参数赋值
		
		query.setFirstResult(0);//从第几条开始，默认从0开始
		query.setMaxResults(5);//一页查询几条
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		
		tr.commit();
	}
	//封装对象传参
	public void queryEmp(Emp emp){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//实体类属性名对应 : 姓名，工作，工资： 组合查询
		String hql = "from Emp where sal >:sal  and  empName like :empName ";
		Query query = session.createQuery(hql);
		//封装实体类提供的方法
		query.setProperties(emp);
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	
	//参数命名绑定
	public void queryList2(double money,String job){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String hql = "from  Emp where sal >:s  and  job=:j";
		//hql : 实体类的类名，属性名
		Query query = session.createQuery(hql);
		//参数赋值
		query.setDouble("s", money);
		//query.setString("j", job);
		query.setParameter("j", job);//不知道类型
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	public void queryList(double money,String job){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//hql : 实体类的类名，属性名
		Query query = session.createQuery("from  Emp where sal >?  and  job=?");
		//参数赋值，从0开始
		query.setDouble(0, money);
		query.setString(1, job);
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	
	//查询员工列表
	public void query(){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//hql : 实体类的类名，属性名
		Query query = session.createQuery("from  Emp where sal >2000  and  job='CLERK'");
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
}





