package com.bdqn.po;

import java.util.Date;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */
//侧重点员工： 多对一
public class Emp implements java.io.Serializable {
	private Long empno;
	private String ename;
	private String job;
	private Double sal;
	//多个员工属于一个部门
	private  Dept dept;

	public Long getEmpno() {
		return empno;
	}

	public void setEmpno(Long empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	


}