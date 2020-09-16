package com.foo.healthApp.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	
	public ValidationException( String errMsg, Throwable throwable) {

		super(throwable);
		this.errMsg = errMsg;

	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	
}
