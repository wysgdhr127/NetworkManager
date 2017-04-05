package com.algoblu.sdwan.linkswitch.basic.vo;

import java.net.URLDecoder;

public class ErrorVo {
	
	//成功标志
	private boolean success = false;
	//返回值
	private String result = "";
	//返回码
	private String errorCode = "0";
	//返回信息
	private String errorMessage = "";



	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		String errorMessage1 = null;
		 try {
			 errorMessage1 = URLDecoder.decode(errorMessage, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.errorMessage = errorMessage1;
	}
	
	
}
