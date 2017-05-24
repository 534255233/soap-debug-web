package com.zlp.soap.controller;

import java.util.Base64;
import java.util.HashMap;
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
@RequestMapping("/material")
public class MaterialController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(MaterialController.class);
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		log.info("--> {}", "hello world");
		return "hello world.";
	}

	@RequestMapping("/plan")
	@ResponseBody
	public HttpCallbackResultVO<String> debug(String url, String ns, String methodName, 
											  String inLifnr, String inWerks, 
											  HttpServletRequest request) {
		
		String encoding = null;
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		if (!StringUtils.isEmpty(account)) {
			String src = account.trim()+":"+passwd.trim();
			log.info("--> {}", src);
			encoding = Base64.getEncoder().encodeToString(src.getBytes());
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
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("<InEkgrp></InEkgrp>");
		sbBuffer.append("<InLifnr><item><Lifnr>"+inLifnr+"</Lifnr></item></InLifnr>");
		sbBuffer.append("<InWerks>"+inWerks+"</InWerks>");
		sbBuffer.append("<OutData></OutData>");
		log.info("--> params = {}", sbBuffer.toString());
		httpClient.setXmlParams(sbBuffer.toString());
		HttpCallbackResultVO<String> response = httpClient.execute(String.class);
		log.info("--> response = {}", response.toString());
		
		return response;
	}
	

}
