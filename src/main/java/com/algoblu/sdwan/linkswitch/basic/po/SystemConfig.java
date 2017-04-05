package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class SystemConfig extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
	
	private Long id;

	private String configName;

	private String configValue;

	private String configDesc;

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id=id;
	}

	public String getConfigName()
	{
		return this.configName;
	}

	public void setConfigName(String configName)
	{
		this.configName=configName;
	}

	public String getConfigValue()
	{
		return this.configValue;
	}

	public void setConfigValue(String configValue)
	{
		this.configValue=configValue;
	}

	public String getConfigDesc()
	{
		return this.configDesc;
	}

	public void setConfigDesc(String configDesc)
	{
		this.configDesc=configDesc;
	}

}
