package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Node extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private String nodeCode;
	private String nodeName;
	private String location;
	private String nodeIp;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public String getNodeCode()
	{
		return this.nodeCode;
	}
	public void setNodeCode(String nodeCode)
	{
		this.nodeCode=nodeCode;
	}
	public String getNodeName()
	{
		return this.nodeName;
	}
	public void setNodeName(String nodeName)
	{
		this.nodeName=nodeName;
	}
	public String getLocation()
	{
		return this.location;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public String getNodeIp()
	{
		return this.nodeIp;
	}
	public void setNodeIp(String nodeIp)
	{
		this.nodeIp=nodeIp;
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
		return "Node [id=" + id + ", nodeCode=" + nodeCode + ", nodeName=" + nodeName + ", location=" + location
				+ ", nodeIp=" + nodeIp + ", status=" + status + "]";
	}

}
