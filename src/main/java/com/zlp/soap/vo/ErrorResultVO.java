package com.zlp.soap.vo;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class ErrorResultVO extends AbstractResultVO {
	
	/***/
	private static final long serialVersionUID = 1L;
	
	public ErrorResultVO () {
		super(CodeEnum.ERROR.getValue(), CodeEnum.ERROR.getDes());
	}
	
	public ErrorResultVO (String message) {
		super(CodeEnum.ERROR.getValue(), CodeEnum.ERROR.getDes(), message);
	}

}
