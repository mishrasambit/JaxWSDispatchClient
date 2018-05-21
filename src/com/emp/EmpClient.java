package com.emp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import com.emp.gen.EmpList;
import com.emp.jaxb.GetAllEmpRequest;
import com.emp.jaxb.GetAllEmpResponse;
import com.emp.jaxb.SetEmpRequest;
import com.emp.jaxb.SetEmpResponse;
public class EmpClient {
	static final private String TRG_NMSPC = "http://emp.com/";
	static final private String SERVICE_NAME = "CEmpWebService";
	static final private String PORT_NAME = "CEmpWebPort";
	static final private String ENDPOINT_URL = "http://localhost:8081/EmpJaxWs/getemp";

	public static void main(String[] args) throws SOAPException, IOException,
			XMLStreamException, JAXBException {
		// TODO Auto-generated method stub
		//soapMessageDispatch();
		sourceDispatch();
		//jaxbDispatch();
		jaxbSetEmpDispatch();
		//jaxbGetAllEmpDispatch();
		//domDispatch();
		//soapMessageGetAllEmpDispatch();
	}
	public static void soapMessageGetAllEmpDispatch() throws SOAPException, IOException, XMLStreamException, JAXBException{
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);

		Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(
				TRG_NMSPC, PORT_NAME), SOAPMessage.class, Service.Mode.MESSAGE);

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage();
		SOAPPart part = request.getSOAPPart();
		SOAPEnvelope envelope = part.getEnvelope();
		SOAPBody body = envelope.getBody();
		SOAPElement operationElem = body.addChildElement("getEmplist", "wsdl",
				TRG_NMSPC);
		//SOAPElement argument = operationElem.addChildElement("arg0", "", "");
		//argument.addTextNode("1234");

		SOAPMessage response = dispatch.invoke(request);
		//Emp emp = parseforEmp(response);
		// response.writeTo(System.out);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String strMsg = new String(out.toByteArray());
		soapMessageParserForAllEmp(response);
		System.out.println("\nstring to :" + strMsg);
		
	}

	private static void soapMessageParserForAllEmp(SOAPMessage response) throws SOAPException, IOException, XMLStreamException, JAXBException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String MESSAGE = new String(out.toByteArray());

		XMLInputFactory xif = XMLInputFactory.newFactory();
		StreamSource xml = new StreamSource(new StringReader(MESSAGE));
		XMLStreamReader xsr = xif.createXMLStreamReader(xml);
		xsr.nextTag();
		while (!xsr.getLocalName().equals("getEmplistResponse")) {
			xsr.nextTag();
			System.out.println(xsr.getLocalName());
		}


    

		JAXBContext jc = JAXBContext.newInstance(EmpList.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<EmpList> jb = unmarshaller.unmarshal(xsr, EmpList.class);
		xsr.close();
		EmpList empList=  jb.getValue();
		
		for(com.emp.gen.Emp emp:empList.getEmplist()){
        	System.out.println("--------------");
        	System.out.println(emp.getId());
    		System.out.println(emp.getName());
    		System.out.println(emp.getDoj());
    		System.out.println(emp.getDept());
    		System.out.println(emp.getSal());
    		System.out.println("--------------");
        }   
	}
	private static void jaxbGetAllEmpDispatch() throws JAXBException {
		// TODO Auto-generated method stub
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);
				
		JAXBContext jc = JAXBContext.newInstance(GetAllEmpRequest.class, EmpList.class);
		Dispatch<Object> dispatch = service.createDispatch(new QName(TRG_NMSPC,
				PORT_NAME), jc, Service.Mode.PAYLOAD);
        
		GetAllEmpRequest request = new GetAllEmpRequest();
		
		GetAllEmpResponse response = (GetAllEmpResponse) dispatch.invoke(request);
        
        EmpList emplist = response.getValue();
        List<com.emp.gen.Emp> elist=emplist.getEmplist();
        for(com.emp.gen.Emp emp:elist){
        	System.out.println("--------------");
        	System.out.println(emp.getId());
    		System.out.println(emp.getName());
    		System.out.println(emp.getDoj());
    		System.out.println(emp.getDept());
    		System.out.println(emp.getSal());
    		System.out.println("--------------");
        }
    }

	private static void jaxbSetEmpDispatch() throws JAXBException {
		// TODO Auto-generated method stub
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);
				
		JAXBContext jc = JAXBContext.newInstance(SetEmpRequest.class, SetEmpResponse.class);
		Dispatch<Object> dispatch = service.createDispatch(new QName(TRG_NMSPC,
				PORT_NAME), jc, Service.Mode.PAYLOAD);
        
		SetEmpRequest request = new SetEmpRequest();
		Emp setEmp = new Emp();
		setEmp.setId("1223");
		setEmp.setName("Satyajit");
		setEmp.setDept("10");
		setEmp.setDoj("10-dec-1998");
		setEmp.setSal("12000");
		request.setArg0(setEmp);
		
		SetEmpResponse response = (SetEmpResponse) dispatch.invoke(request);
        
        Emp emp = (Emp)response.getValue();
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getDoj());
		System.out.println(emp.getDept());
		System.out.println(emp.getSal());
	}

	private static void domDispatch() throws JAXBException {
		// TODO Auto-generated method stub
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);
				
		
		/*Dispatch<Object> dispatch = service.createDispatch(new QName(TRG_NMSPC,
				PORT_NAME), jc, Service.Mode.PAYLOAD);
        
		GetEmpRequest request = new GetEmpRequest();
		request.setArg0("1234");
		
		GetEmpResponse response = (GetEmpResponse) dispatch.invoke(request);
        
        Emp emp = (Emp)response.getValue();
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getDoj());
		System.out.println(emp.getDept());
		System.out.println(emp.getSal());*/
		
	}

/*	private static void jaxbDispatch() throws XMLStreamException, JAXBException {
		
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);
				
		JAXBContext jc = JAXBContext.newInstance(GetEmpRequest.class, GetEmpResponse.class);
		Dispatch<Object> dispatch = service.createDispatch(new QName(TRG_NMSPC,
				PORT_NAME), jc, Service.Mode.PAYLOAD);
        
		GetEmpRequest request = new GetEmpRequest();
		request.setArg0("1223");
		
		GetEmpResponse response = (GetEmpResponse) dispatch.invoke(request);
        
        Emp emp = (Emp)response.getValue();
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getDoj());
		System.out.println(emp.getDept());
		System.out.println(emp.getSal());
		
	}*/
	
	
	
	

	public static void sourceDispatch() throws JAXBException,
			XMLStreamException {
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);

		Dispatch<Source> dispatch = service.createDispatch(new QName(TRG_NMSPC,
				PORT_NAME), Source.class, Service.Mode.PAYLOAD);

		String reqString = "<emp:getEmp xmlns:emp='http://emp.com/'><arg0>12</arg0></emp:getEmp>";

		StreamSource source = make_stream_source(reqString);
		Source result = dispatch.invoke(source);

		String resultString = sourceToXMLString(result);
		System.out.println(resultString);
		parseSourceString(resultString);

	}

	private static StreamSource make_stream_source(String reqString) {
		// TODO Auto-generated method stub
		return new StreamSource(new StringReader(reqString));
	}

	private static String sourceToXMLString(Source result) {
		String xmlResult = null;
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			OutputStream out = new ByteArrayOutputStream();
			StreamResult streamResult = new StreamResult();
			streamResult.setOutputStream(out);
			transformer.transform(result, streamResult);
			xmlResult = streamResult.getOutputStream().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return xmlResult;
	}

	private static void parseSourceString(String resultString)
			throws XMLStreamException, JAXBException {

		Reader reader = new StringReader(resultString);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		XMLStreamReader xsr = factory.createXMLStreamReader(reader);
		xsr.nextTag();
		while (!xsr.getLocalName().equals("return")) {
			xsr.nextTag();
		}
		JAXBContext jc = JAXBContext.newInstance(Emp.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<Emp> jb = unmarshaller.unmarshal(xsr, Emp.class);
		xsr.close();

		Emp emp = jb.getValue();
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getDoj());
		System.out.println(emp.getDept());
		System.out.println(emp.getSal());
	}

	public static void soapMessageDispatch() throws SOAPException, IOException,
			XMLStreamException, JAXBException {
		Service service = Service.create(new QName(TRG_NMSPC, SERVICE_NAME));
		service.addPort(new QName(TRG_NMSPC, PORT_NAME),
				SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_URL);

		Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(
				TRG_NMSPC, PORT_NAME), SOAPMessage.class, Service.Mode.MESSAGE);

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage();
		SOAPPart part = request.getSOAPPart();
		SOAPEnvelope envelope = part.getEnvelope();
		SOAPBody body = envelope.getBody();
		SOAPElement operationElem = body.addChildElement("getEmp", "wsdl",
				TRG_NMSPC);
		SOAPElement argument = operationElem.addChildElement("arg0", "", "");
		argument.addTextNode("1234");

		SOAPMessage response = dispatch.invoke(request);
		//Emp emp = parseforEmp(response);
		// response.writeTo(System.out);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String strMsg = new String(out.toByteArray());
		soapMessageParser(response);
		System.out.println("\nstring to :" + strMsg);
	}

	public static void soapMessageParser(SOAPMessage response)
			throws SOAPException, XMLStreamException, IOException,
			JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String MESSAGE = new String(out.toByteArray());

		XMLInputFactory xif = XMLInputFactory.newFactory();
		StreamSource xml = new StreamSource(new StringReader(MESSAGE));
		XMLStreamReader xsr = xif.createXMLStreamReader(xml);
		xsr.nextTag();
		while (!xsr.getLocalName().equals("return")) {
			xsr.nextTag();
		}

		JAXBContext jc = JAXBContext.newInstance(Emp.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<Emp> jb = unmarshaller.unmarshal(xsr, Emp.class);
		xsr.close();

		Emp emp = jb.getValue();
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getDoj());
		System.out.println(emp.getDept());
		System.out.println(emp.getSal());
	}

	
}
