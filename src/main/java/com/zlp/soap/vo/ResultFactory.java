package com.zlp.soap.vo;

/**
 * 
 * @author zhoulongpeng
 *
 */
public class ResultFactory {
	
	public static AbstractResultVO createSucessResult() {
		return new SuccessResultVO();
	}
	
	public static AbstractResultVO createErrorResult() {
		return new ErrorResultVO();
	}
	
	public static AbstractResultVO createExceptionResult() {
		return new ExceptionResultVO();
	}

}
