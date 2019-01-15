package com.bdqn.po;

import java.util.ArrayList;
import java.util.List;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */
//侧重点在部门：一对多
public class Dept implements java.io.Serializable {
	private Long deptno;
	private String dname;
	private String loc;
	//一个部门多个员工 ： List，Set（唯一，无序）
//	private  Set<Emp>  emps = new  HashSet<Emp>();
	private  List<Emp> emps = new ArrayList<Emp>();
	
	public List<Emp> getEmps() {
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	/*public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}*/
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}