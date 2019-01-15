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
		System.out.println("��ʼ��64M��"+Runtime.getRuntime().totalMemory());
		byte b[] = new byte[600*1024*1024]; 
		System.out.println("----------");
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println("��ʼ��64M��"+Runtime.getRuntime().totalMemory());
		System.out.println("----------");
	
	}
	//ͶӰ��ѯ�� һ�� : Object  �����У����У� Object[];  ���ض�������
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
	
	//�ܼ�¼
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
		//Ψһһ�����
		Object obj = query.uniqueResult();
		long  count = (Long)obj;
		System.out.println(count);
		tr.commit();
	}
	//��̬sql
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
		query.setProperties(emp); //������ֵ
		
		query.setFirstResult(0);//�ӵڼ�����ʼ��Ĭ�ϴ�0��ʼ
		query.setMaxResults(5);//һҳ��ѯ����
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		
		tr.commit();
	}
	//��װ���󴫲�
	public void queryEmp(Emp emp){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//ʵ������������Ӧ : ���������������ʣ� ��ϲ�ѯ
		String hql = "from Emp where sal >:sal  and  empName like :empName ";
		Query query = session.createQuery(hql);
		//��װʵ�����ṩ�ķ���
		query.setProperties(emp);
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	
	//����������
	public void queryList2(double money,String job){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String hql = "from  Emp where sal >:s  and  job=:j";
		//hql : ʵ�����������������
		Query query = session.createQuery(hql);
		//������ֵ
		query.setDouble("s", money);
		//query.setString("j", job);
		query.setParameter("j", job);//��֪������
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	public void queryList(double money,String job){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//hql : ʵ�����������������
		Query query = session.createQuery("from  Emp where sal >?  and  job=?");
		//������ֵ����0��ʼ
		query.setDouble(0, money);
		query.setString(1, job);
		
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
	
	//��ѯԱ���б�
	public void query(){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//hql : ʵ�����������������
		Query query = session.createQuery("from  Emp where sal >2000  and  job='CLERK'");
		List<Emp> emplist = query.list();
		for(Emp e :emplist) {
			System.out.println(e.getEmpName()+"--"+e.getSal());
		}	
		tr.commit();
	}
}





