package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Function extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private Long parentFunctionId;
	private Integer functionLevel;
	private String functionType;
	private String functionName;
	private String roleString;
	private String functionUrl;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public Long getParentFunctionId()
	{
		return this.parentFunctionId;
	}
	public void setParentFunctionId(Long parentFunctionId)
	{
		this.parentFunctionId=parentFunctionId;
	}
	public Integer getFunctionLevel()
	{
		return this.functionLevel;
	}
	public void setFunctionLevel(Integer functionLevel)
	{
		this.functionLevel=functionLevel;
	}
	public String getFunctionType()
	{
		return this.functionType;
	}
	public void setFunctionType(String functionType)
	{
		this.functionType=functionType;
	}
	public String getFunctionName()
	{
		return this.functionName;
	}
	public void setFunctionName(String functionName)
	{
		this.functionName=functionName;
	}
	public String getRoleString()
	{
		return this.roleString;
	}
	public void setRoleString(String roleString)
	{
		this.roleString=roleString;
	}
	public String getFunctionUrl()
	{
		return this.functionUrl;
	}
	public void setFunctionUrl(String functionUrl)
	{
		this.functionUrl=functionUrl;
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
