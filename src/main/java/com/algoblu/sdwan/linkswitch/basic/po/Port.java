package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Port extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
	








	{
		return this.id;
	}

	{
		this.id=id;
	}

	{
		return this.nodeId;
	}

	{
		this.nodeId=nodeId;
	}

	{
		return this.bridgeId;
	}

	{
		this.bridgeId=bridgeId;
	}

	{
		return this.portCode;
	}

	{
		this.portCode=portCode;
	}

	{
		return this.portName;
	}

	{
		this.portName=portName;
	}

	{
		return this.portIp;
	}

	{
		this.portIp=portIp;
	}

	{
		return this.macAddress;
	}

	{
		this.macAddress=macAddress;
	}

	{
		return this.status;
	}

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