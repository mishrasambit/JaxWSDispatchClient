package com.emp.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.emp.gen.Emp;

@XmlRootElement(namespace="http://emp.com/")
public class GetEmpResponse {
	private Emp value;
	 
    @XmlElement(name="return")
    public Emp getValue() {
        return value;
    }
 
    public void setValue(Emp value) {
        this.value = value;
    }
 

}
