package com.algoblu.sdwan.linkswitch.basic.rpcEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者：王阳 E-mail:282404989@qq.com
 * @version 创建时间：20 Jan 2017 14:23:39 类说明
 */
@SuppressWarnings("serial")
public class AlgobluNode implements Serializable{

	private String algobluNodeId;

	private String algobluNodeName;

	private String algobluNodeIp;

	private String algobluNodeRef;
	
	private String algobluSrcNodeName;

	private List<AlgobluPort> portList;

	public String getAlgobluNodeId() {
		return algobluNodeId;
	}

	public void setAlgobluNodeId(String algobluNodeId) {
		this.algobluNodeId = algobluNodeId;
	}

	public String getAlgobluNodeName() {
		return algobluNodeName;
	}

	public void setAlgobluNodeName(String algobluNodeName) {
		this.algobluNodeName = algobluNodeName;
	}

	public String getAlgobluNodeIp() {
		return algobluNodeIp;
	}

	public void setAlgobluNodeIp(String algobluNodeIp) {
		this.algobluNodeIp = algobluNodeIp;
	}

	public String getAlgobluNodeRef() {
		return algobluNodeRef;
	}

	public void setAlgobluNodeRef(String algobluNodeRef) {
		this.algobluNodeRef = algobluNodeRef;
	}

	public List<AlgobluPort> getPortList() {
		return portList;
	}

	public void setPortList(List<AlgobluPort> portList) {
		this.portList = portList;
	}

	public String getAlgobluSrcNodeName() {
		return algobluSrcNodeName;
	}

	public void setAlgobluSrcNodeName(String algobluSrcNodeName) {
		this.algobluSrcNodeName = algobluSrcNodeName;
	}

	@Override
	public String toString() {
		return "AlgobluNode [algobluNodeId=" + algobluNodeId + ", algobluNodeName=" + algobluNodeName
				+ ", algobluNodeIp=" + algobluNodeIp + ", algobluNodeRef=" + algobluNodeRef + ", algobluSrcNodeName="
				+ algobluSrcNodeName + ", portList=" + portList + "]";
	}
	
}
