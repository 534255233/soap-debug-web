package com.zlp.soap.controller;

import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.qqt.controller.BaseController;
import com.qqt.service.core.HttpClientFactory;
import com.qqt.service.core.HttpRequest;
import com.qqt.service.vo.HttpCallbackResultVO;

/**
 * 
 * @author zhoulongpeng
 *
 */
@Controller
@EnableWebMvc
@RequestMapping("/soap")
public class SoapController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(SoapController.class);
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		log.info("--> {}", "hello world");
		return "hello world.";
	}

	@RequestMapping("/debug")
	@ResponseBody
	public HttpCallbackResultVO<String> debug(String url, String ns, String methodName, HttpServletRequest request) {
		Map<String, String> params = paramsAndValue(request);
		
		String encoding = null;
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		if (!StringUtils.isEmpty(account)) {
			String src = account.trim()+":"+passwd.trim();
			log.info("--> {}", src);
			encoding = Base64.getEncoder().encodeToString(src.getBytes());
//			Authenticator.setDefault(new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(account, passwd.toCharArray());
//				}
//			});
		}
		
		HttpRequest httpClient = HttpClientFactory.createSoapRequest();
		if (!StringUtils.isEmpty(encoding)) {
			Map<String, String> headers = new HashMap<>();
			headers.put("Authorization", "Basic " + encoding);
			log.info("--> headers {}", headers);
			httpClient.addHeaders(headers);
		}
		httpClient.setUrl(url);
		httpClient.setNamespace(ns);
		httpClient.setMethodName(methodName);
		httpClient.setPairParams(params);
//		httpClient.setReturnType(ReturnType.STRING);
		HttpCallbackResultVO<String> response = httpClient.execute(String.class);
		
		return response;
	}
	
	@RequestMapping("/material")
	@ResponseBody
	public HttpCallbackResultVO<String> debugMaterial() {
		
		HttpRequest httpClient = HttpClientFactory.createSoapRequest();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-type", "text/xml; charset=UTF-8");
		headers.put("Accept", "text/xml");
		httpClient.addHeaders(headers);
		
		httpClient.setUrl("http://10.1.1.60:8000/sap/bc/srt/rfc/sap/ZSRM_MATNR_OUT?sap-client=620&wsdl=1.1");
		httpClient.setMethodName("ZsrmMatnrOut");
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
		httpClient.setXmlParams(params);
//		httpClient.setReturnType(ReturnType.STRING);
		HttpCallbackResultVO<String> response = httpClient.execute(String.class);
		
		return response;
	}
	
	private Map<String, String> paramsAndValue(HttpServletRequest request) {
    	Map<String, String> argus = new LinkedHashMap<>();
    	Enumeration<String> params = request.getParameterNames();
    	while(params != null && params.hasMoreElements()) {
    		String parameter = params.nextElement();
    		if("url".equals(parameter) || 
    		   "ns".equals(parameter) || 
    		   "methodName".equals(parameter) || 
    		   "account".equals(parameter) || 
    		   "passwd".equals(parameter)) continue;
    		argus.put(parameter, request.getParameter(parameter));
    	}
    	return argus;
    }

}
