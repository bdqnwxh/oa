package com.bdqn.po;

public class Emp {
	private int empNo;
	private String empName;
	private String job;
	private Double sal;
	Dept d;
	public Emp(){
		
	}
	
	public Emp(String empName,String job){
		this.empName = empName;
		this.job = job;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	
	
}
