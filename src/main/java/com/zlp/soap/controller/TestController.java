package com.zlp.soap.controller;


import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.w3c.dom.Document;

import com.qqt.controller.BaseController;
import com.qqt.service.server.ClientAddAuthInInterceptor;

/**
 * 
 * @author zhoulongpeng
 *
 */
@Controller
@EnableWebMvc
@RequestMapping("/test")
public class TestController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		log.info("--> {}", "hello world");
		return "hello world.";
	}

	@RequestMapping("/debug1")
	@ResponseBody
	public String debug1() {
		
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient("http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUT?sap-client=620&wsdl=1.1");
			client.getOutInterceptors().add(new ClientAddAuthInInterceptor());

			if (client != null) {
				System.out.println("client Interceptors: " + client.getInInterceptors().size());
			}
			
//			TableOfZsrmMatnrS table = new TableOfZsrmMatnrS();

			Object[] res = client.invoke("ZsrmMatnrOut", "2017-03-16", "", null);
			
			log.info("--> response = {}", res.toString());
			
			System.out.println("response: " + res[0]);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

		return "call finish.";
		
	}
	
	@RequestMapping("/debug2")
	@ResponseBody
	public String debug2() {
		
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient("http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUT?wsdl=1.1");
			client.getOutInterceptors().add(new ClientAddAuthInInterceptor());

			if (client != null) {
				System.out.println("client Interceptors: " + client.getInInterceptors().size());
			}

			Object[] res = client.invoke("ZsrmMatnrOut", "2017-03-16", "", null);
			
			log.info("--> response = {}", res.toString());
			
			System.out.println("response: " + res[0]);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

		return "call finish.";
		
	}
	
	@RequestMapping("/debug3")
	@ResponseBody
	public String debug3() {
		
		String result = null;
		try {
			 String ns = "urn:sap-com:document:sap:soap:functions:mc-style";  
	         String wsdlUrl = "http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUTE?sap-client=620&wsdl=1.1";  
	         //1、创建服务(Service)  
	         URL url = new URL(wsdlUrl);  
	         QName sname = new QName(ns,"ZSRM_MATNR_OUTService");  
	         Service service = Service.create(url,sname);  
	                       
	         //2、创建Dispatch  
	         Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"ZSRM_MATNR_OUTSoapBinding"),SOAPMessage.class,Service.Mode.MESSAGE);  
	                       
	         //3、创建SOAPMessage  
	         SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();  
	         SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();  
	         SOAPBody body = envelope.getBody();  
	                       
	         //4、创建QName来指定消息中传递数据  
	         QName ename = new QName(ns,"ZsrmMatnrOut","zlp");//<nn:add xmlns="xx"/>  
	         SOAPBodyElement ele = body.addBodyElement(ename);  
	         // 传递参数  
	         ele.addChildElement("InDate").setValue("2017-03-16");    
	         ele.addChildElement("InMatnr").setValue("");  
	         ele.addChildElement("OutMatnr").setValue("");  
	         msg.writeTo(System.out);  
	         log.info("--> request data: {}", msg.toString());
	         System.out.println("\n invoking.....");  
	                               
	         //5、通过Dispatch传递消息,会返回响应消息  
	         SOAPMessage response = dispatch.invoke(msg);  
	         response.writeTo(System.out);  
	         log.info("--> response data: {}", response.toString());
	         result = response.toString();
	         System.out.println();  
	                       
	         //6、响应消息处理,将响应的消息转换为dom对象  
	         Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();  
//	         String str = doc.getElementsByTagName("ns:return").item(0).getTextContent();  
	         String str = doc.getElementsByTagName("return").item(0).getTextContent();  
	         System.out.println(str); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			log.error("--> {}", e.getLocalizedMessage());
		}
		return result;
	}
	
	@RequestMapping("/debug4")
	@ResponseBody
	public String debug4() {
		
		String result = "error.";
		try {
//			Authenticator.setDefault(new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("username", "password".toCharArray());
//				}
//			});
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUT_SERVICE").openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "text/xml");
			
			connection.setDoOutput(true);
			
			String params = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">";
			params += "<soapenv:Header/>"; 
			params += "<soapenv:Body>"; 
			params += "<urn:ZsrmMatnrOut>"; 
			params += "<InDate>2017-03-16</InDate>"; 
			params += "<InMatnr></InMatnr>"; 
			params += "<OutMatnr></OutMatnr>"; 
			params += "</urn:ZsrmMatnrOut>"; 
			params += "</soapenv:Body>"; 
			params += "</soapenv:Envelope>";
			
			connection.getOutputStream().write(params.getBytes());
			
			byte[] bs = new byte[connection.getInputStream().available()];
			connection.getInputStream().read(bs);
			return new String(bs);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			log.error("--> {}", e.getLocalizedMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/debug5")
	@ResponseBody
	public String debug5() {
		
		String result = null;
		try {
			String ns = "urn:sap-com:document:sap:soap:functions:mc-style";  
	         String wsdlUrl = "http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUT_SERVICE?wsdl=1.1";  
	         //1、创建服务(Service)  
	         URL url = new URL(wsdlUrl);  
	         QName sname = new QName(ns);  
	         Service service = Service.create(url,sname);  
	                       
	         //2、创建Dispatch  
	         Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns),SOAPMessage.class,Service.Mode.MESSAGE);  
	                       
	         //3、创建SOAPMessage  
	         SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();  
	         SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();  
	         SOAPBody body = envelope.getBody(); 
	                       
	         //4、创建QName来指定消息中传递数据  
	         QName ename = new QName(ns,"ZsrmMatnrOut","zlp");//<nn:add xmlns="xx"/>  
	         SOAPBodyElement ele = body.addBodyElement(ename);  
	         // 传递参数  
	         ele.addChildElement("InDate").setValue("2017-03-16");    
	         ele.addChildElement("InMatnr").setValue("");  
	         ele.addChildElement("OutMatnr").setValue("");  
	         msg.writeTo(System.out);  
	         log.info("--> request data: {}", msg.toString());
	         System.out.println("\n invoking.....");  
	                               
	         //5、通过Dispatch传递消息,会返回响应消息  
	         SOAPMessage response = dispatch.invoke(msg);  
	         response.writeTo(System.out);  
	         log.info("--> response data: {}", response.toString());
	         result = response.toString();
	         System.out.println();  
	                       
	         //6、响应消息处理,将响应的消息转换为dom对象  
	         Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();  
//	         String str = doc.getElementsByTagName("ns:return").item(0).getTextContent();  
	         String str = doc.getElementsByTagName("return").item(0).getTextContent();  
	         System.out.println(str); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			log.error("--> {}", e.getLocalizedMessage());
		}
		return result;
	}
	
	
}
