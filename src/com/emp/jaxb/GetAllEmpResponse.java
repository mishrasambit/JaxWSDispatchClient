package com.emp.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.emp.gen.EmpList;
@XmlRootElement(namespace = "http://emp.com/")
public class GetAllEmpResponse {
	private EmpList value;
	 
    @XmlElement(name="return")
    public EmpList getValue() {
        return value;
    }
 
    public void setValue(EmpList value) {
        this.value = value;
    }
}
