package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Port extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private Long nodeId;
	private Long bridgeId;
	private String portCode;
	private String portName;
	private String portIp;
	private String macAddress;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public Long getNodeId()
	{
		return this.nodeId;
	}
	public void setNodeId(Long nodeId)
	{
		this.nodeId=nodeId;
	}
	public Long getBridgeId()
	{
		return this.bridgeId;
	}
	public void setBridgeId(Long bridgeId)
	{
		this.bridgeId=bridgeId;
	}
	public String getPortCode()
	{
		return this.portCode;
	}
	public void setPortCode(String portCode)
	{
		this.portCode=portCode;
	}
	public String getPortName()
	{
		return this.portName;
	}
	public void setPortName(String portName)
	{
		this.portName=portName;
	}
	public String getPortIp()
	{
		return this.portIp;
	}
	public void setPortIp(String portIp)
	{
		this.portIp=portIp;
	}
	public String getMacAddress()
	{
		return this.macAddress;
	}
	public void setMacAddress(String macAddress)
	{
		this.macAddress=macAddress;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}

	@Override
	public String toString() {
		return "Port [id=" + id + ", nodeId=" + nodeId + ", bridgeId=" + bridgeId + ", portCode=" + portCode
				+ ", portName=" + portName + ", portIp=" + portIp + ", macAddress=" + macAddress + ", status=" + status
				+ "]";
	}

}
