package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Node extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
	






	{
		return this.id;
	}

	{
		this.id=id;
	}

	{
		return this.nodeCode;
	}

	{
		this.nodeCode=nodeCode;
	}

	{
		return this.nodeName;
	}

	{
		this.nodeName=nodeName;
	}

	{
		return this.location;
	}

	{
		this.location=location;
	}

	{
		return this.nodeIp;
	}

	{
		this.nodeIp=nodeIp;
	}

	{
		return this.status;
	}

	{
		this.status=status;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", nodeCode=" + nodeCode + ", nodeName=" + nodeName + ", location=" + location
				+ ", nodeIp=" + nodeIp + ", status=" + status + "]";
	}

}