package com.cjgod.candytest.exception;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 106300604201064975L;
	
	private String retCd; // 异常对应的返回码
	private String msgDes; // 异常对应的描述信息

	public BaseException() {
	        super();
	    }

	public BaseException(String message) {
	        super(message);
	        msgDes = message;
	    }

	public BaseException(String retCd, String msgDes) {
	        super();
	        this.retCd = retCd;
	        this.msgDes = msgDes;
	    }

	public String getRetCd() {
		return retCd;
	}

	public String getMsgDes() {
		return msgDes;
	}
}
