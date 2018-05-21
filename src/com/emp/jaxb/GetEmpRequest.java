package com.emp.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://emp.com/", name = "getEmp")
public class GetEmpRequest {
	private String arg0;

	public String getArg0() {
		return arg0;
	}

	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}
}
