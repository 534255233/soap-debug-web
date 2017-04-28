package com.zlp.soap.vo;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class SuccessResultVO extends AbstractResultVO {
	
	/***/
	private static final long serialVersionUID = 1L;
	
	public SuccessResultVO () {
		super(CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS.getDes());
	}
	
	public SuccessResultVO (String message) {
		super(CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS.getDes(), message);
	}

}
