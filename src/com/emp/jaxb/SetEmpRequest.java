package com.emp.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

import com.emp.Emp;
@XmlRootElement(namespace = "http://emp.com/", name = "setEmp")
public class SetEmpRequest {

	private Emp arg0;
	 
    public Emp getArg0() {
        return arg0;
    }
 
    public void setArg0(Emp arg0) {
        this.arg0 = arg0;
    }
}
