package com.zyh.beautycits.exception;


/**
 * 自定义异常
 * 
 */

public class MyException extends Exception {

	private static final long serialVersionUID = 4975410684989748541L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable throwable){
	    super(throwable);
	}
	 
	public MyException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public Throwable fillInStackTrace() {    
        return this;    
    } 
	
}
