package com.zlp.soap.controller;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
		
		HttpRequest httpClient = HttpClientFactory.createSoapRequest();
		httpClient.setUrl(url);
		httpClient.setNamespace(ns);
		httpClient.setMethodName(methodName);
		httpClient.setPairParams(params);
//		httpClient.setReturnType(ReturnType.STRING);
		HttpCallbackResultVO<String> response = httpClient.execute(String.class);
		
		return response;
	}
	
	private Map<String, String> paramsAndValue(HttpServletRequest request) {
    	Map<String, String> argus = new HashMap<>();
    	Enumeration<String> params = request.getParameterNames();
    	while(params != null && params.hasMoreElements()) {
    		String parameter = params.nextElement();
    		if("url".equals(parameter) || "ns".equals(parameter) || "methodName".equals(parameter)) continue;
    		argus.put(parameter, request.getParameter(parameter));
    	}
    	return argus;
    }

}
