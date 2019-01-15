package com.bdqn.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

//orm ： 实体类---表
@Entity
@Table(name="dept")
public class Department {
			@Id
			@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
			@SequenceGenerator(name="seq",sequenceName="seq_emp")
			private int deptNo;
			@Column(name="dname")
			private String dname;
			@Transient  //忽略与数据库的对应
			private String loc;
			//一对多   //mappedBy="dept":将关联关系的控制权交给Emp类这一方来维护
			@OneToMany(mappedBy="dept",cascade=CascadeType.MERGE)
			private Set<Employee> emplist = new HashSet<Employee>();
			
			public Set<Employee> getEmplist() {
				return emplist;
			}
			public void setEmplist(Set<Employee> emplist) {
				this.emplist = emplist;
			}
			public int getDeptNo() {
				return deptNo;
			}
			public void setDeptNo(int deptNo) {
				this.deptNo = deptNo;
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
