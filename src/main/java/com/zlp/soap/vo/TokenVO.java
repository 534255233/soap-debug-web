package com.zlp.soap.vo;

import java.io.Serializable;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class TokenVO implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;
	
	private String token;
	
	private String message;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
