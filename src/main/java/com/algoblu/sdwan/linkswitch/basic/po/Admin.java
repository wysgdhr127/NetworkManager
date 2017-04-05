package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Admin extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private String username;
	private String password;
	private String realName;
	private String adminEmail;
	private String functionPrivilege;
	private Integer createTime;
	private Integer deleteTime;
	private Integer lastLoginTime;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getRealName()
	{
		return this.realName;
	}
	public void setRealName(String realName)
	{
		this.realName=realName;
	}
	public String getAdminEmail()
	{
		return this.adminEmail;
	}
	public void setAdminEmail(String adminEmail)
	{
		this.adminEmail=adminEmail;
	}
	public String getFunctionPrivilege()
	{
		return this.functionPrivilege;
	}
	public void setFunctionPrivilege(String functionPrivilege)
	{
		this.functionPrivilege=functionPrivilege;
	}
	public Integer getCreateTime()
	{
		return this.createTime;
	}
	public void setCreateTime(Integer createTime)
	{
		this.createTime=createTime;
	}
	public Integer getDeleteTime()
	{
		return this.deleteTime;
	}
	public void setDeleteTime(Integer deleteTime)
	{
		this.deleteTime=deleteTime;
	}
	public Integer getLastLoginTime()
	{
		return this.lastLoginTime;
	}
	public void setLastLoginTime(Integer lastLoginTime)
	{
		this.lastLoginTime=lastLoginTime;
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
