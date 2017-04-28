package com.zlp.soap.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.zlp.soap.vo.AbstractResultVO;

/**
 * 
 * @author zhoulongpeng
 *
 */
@WebService
public interface DemoSoapService {
	
	AbstractResultVO notify(@WebParam(name="keyId") String keyId, 
							@WebParam(name="webService") String webService, 
							@WebParam(name="param1") String param1, 
							@WebParam(name="param2") String param2, 
							@WebParam(name="param3") String param3);

	AbstractResultVO notifyPasswod(@WebParam(name="userId") String userId, 
								   @WebParam(name="account") String webService, 
								   @WebParam(name="token") String token, 
								   @WebParam(name="body") String body);
	
}
