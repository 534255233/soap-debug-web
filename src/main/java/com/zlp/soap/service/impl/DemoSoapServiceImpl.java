package com.zlp.soap.service.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zlp.soap.service.DemoSoapService;
import com.zlp.soap.vo.AbstractResultVO;
import com.zlp.soap.vo.ResultFactory;

/**
 * 
 * @author zhoulongpeng
 */
@Service("demoSoapService")
@WebService(name = "demoSoapService", 
			serviceName = "DemoSoapService", 
			targetNamespace = "http://service.soap.zlp.com/",
			endpointInterface = "com.zlp.soap.service.DemoSoapService")
public class DemoSoapServiceImpl implements DemoSoapService {
	
	private static Logger log = LoggerFactory.getLogger(DemoSoapServiceImpl.class);
	
	@Override
	public AbstractResultVO notify(@WebParam(name="keyId") String keyId, 
								   @WebParam(name="webService") String webService, 
								   @WebParam(name="param1") String param1, 
								   @WebParam(name="param2") String param2, 
								   @WebParam(name="param3") String param3) {
		AbstractResultVO vo  = ResultFactory.createSucessResult();
		
		vo.setMessage("keyId: "+keyId+" webService:"+webService+" param1:"+param1+" param2:"+param2+" param3 :"+param3);
		
		return vo;
	}

	@Override
	public AbstractResultVO notifyPasswod(@WebParam(name="userId") String userId, 
										  @WebParam(name="account") String account, 
										  @WebParam(name="token") String token, 
										  @WebParam(name="body") String body) {
		
		log.info(userId);
		log.info(account);
		log.info(token);
		log.info(body);
		
		AbstractResultVO vo  = ResultFactory.createSucessResult();
		vo.setMessage(body);
		
		return vo;
	}
	
	@Override
	public String sayHi(String name) {
		log.info("--> hello, ", name);
		String result = "hello, "+name;
		return result;
	}
	
	@Override
	public AbstractResultVO test() {
		log.info("--> test");
		AbstractResultVO vo  = ResultFactory.createSucessResult();
		vo.setMessage("test.");
		return vo;
	}

}
