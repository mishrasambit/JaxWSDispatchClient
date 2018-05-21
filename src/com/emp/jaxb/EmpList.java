package com.emp.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.emp.Emp;

@XmlRootElement
public class EmpList {
	List<Emp> emplist;

	public List<Emp> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<Emp> emplist) {
		this.emplist = emplist;
	}
	
}
