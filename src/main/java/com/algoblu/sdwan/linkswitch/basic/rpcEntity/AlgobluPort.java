package com.algoblu.sdwan.linkswitch.basic.rpcEntity;

import java.io.Serializable;

/**
 * @author 作者：王阳 E-mail:282404989@qq.com
 * @version 创建时间：20 Jan 2017 14:27:17 类说明
 */
@SuppressWarnings("serial")
public class AlgobluPort implements Serializable{
	
	private String algobluPortRef;

	private String algobluPortNum;

	private String physicalAddress;

	private String algobluPortName;
	
	private String algobluSrcPortNum;

	public String getAlgobluPortRef() {
		return algobluPortRef;
	}

	public void setAlgobluPortRef(String algobluPortRef) {
		this.algobluPortRef = algobluPortRef;
	}

	public String getAlgobluPortNum() {
		return algobluPortNum;
	}

	public void setAlgobluPortNum(String algobluPortNum) {
		this.algobluPortNum = algobluPortNum;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getAlgobluPortName() {
		return algobluPortName;
	}

	public void setAlgobluPortName(String algobluPortName) {
		this.algobluPortName = algobluPortName;
	}

	public String getAlgobluSrcPortNum() {
		return algobluSrcPortNum;
	}

	public void setAlgobluSrcPortNum(String algobluSrcPortNum) {
		this.algobluSrcPortNum = algobluSrcPortNum;
	}
}
