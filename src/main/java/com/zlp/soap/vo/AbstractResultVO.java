package com.zlp.soap.vo;

import java.io.Serializable;

/**
 * 
 * @author zhoulongpeng
 *
 */
public abstract class AbstractResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 错误代码
	 */
	protected int code;
	/**
	 * 返回结果
	 * error:     失败
	 * success:   成功
	 * exception: 异常
	 */
	protected String result;
	/**
	 * 错误信息
	 */
	protected String message;
	
	public AbstractResultVO() {}
	
	public AbstractResultVO(int code, String result) {
		this.code = code;
		this.result = result;
	}
	
	public AbstractResultVO(String result, String message) {
		this.result = result;
		this.message = message;
	}
	
	public AbstractResultVO(int code, String result, String message) {
		this.code = code;
		this.result = result;
		this.message = message;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @author zhoulongpeng
	 * value:  code值
	 * des:    result对应的值
	 *
	 */
	public enum CodeEnum {
		
		SUCCESS("success", 200), ERROR("error", 500), EXCEPTION("exception", 700);
		
		private String des;
		
		private int value;
		
		private CodeEnum(String des, int value){
			this.des = des;
			this.value = value;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	}

}
