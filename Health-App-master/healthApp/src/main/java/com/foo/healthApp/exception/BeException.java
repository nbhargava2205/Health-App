package com.foo.healthApp.exception;

public class BeException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	
	public BeException( String errMsg, Throwable throwable) {

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
