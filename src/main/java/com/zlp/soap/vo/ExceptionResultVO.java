package com.zlp.soap.vo;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class ExceptionResultVO extends AbstractResultVO {
	
	/***/
	private static final long serialVersionUID = 1L;
	
	public ExceptionResultVO () {
		super(CodeEnum.EXCEPTION.getValue(), CodeEnum.EXCEPTION.getDes());
	}
	
	public ExceptionResultVO (String message) {
		super(CodeEnum.EXCEPTION.getValue(), CodeEnum.EXCEPTION.getDes(), message);
	}

}
