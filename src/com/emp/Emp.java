package com.emp;

public class Emp {
	private String id;
	private String name;
	private String dept;
	private String doj;
	private String sal;
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(String id, String name, String dept, String doj, String sal) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.doj = doj;
		this.sal = sal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}

}
