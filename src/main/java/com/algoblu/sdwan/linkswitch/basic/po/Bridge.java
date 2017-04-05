package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Bridge extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private String bridgeCode;
	private String bridgeDesc;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public String getBridgeCode()
	{
		return this.bridgeCode;
	}
	public void setBridgeCode(String bridgeCode)
	{
		this.bridgeCode=bridgeCode;
	}
	public String getBridgeDesc()
	{
		return this.bridgeDesc;
	}
	public void setBridgeDesc(String bridgeDesc)
	{
		this.bridgeDesc=bridgeDesc;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}

}
