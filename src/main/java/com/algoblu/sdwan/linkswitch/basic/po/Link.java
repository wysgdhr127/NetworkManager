package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Link extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private Long currentPortId;
	private Long targetPortId;
	private String linkName;
	private Boolean isActive;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public Long getCurrentPortId()
	{
		return this.currentPortId;
	}
	public void setCurrentPortId(Long currentPortId)
	{
		this.currentPortId=currentPortId;
	}
	public Long getTargetPortId()
	{
		return this.targetPortId;
	}
	public void setTargetPortId(Long targetPortId)
	{
		this.targetPortId=targetPortId;
	}
	public String getLinkName()
	{
		return this.linkName;
	}
	public void setLinkName(String linkName)
	{
		this.linkName=linkName;
	}
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
		return "Link [id=" + id + ", currentPortId=" + currentPortId + ", targetPortId=" + targetPortId + ", linkName="
				+ linkName + ", isActive=" + isActive + ", status=" + status + "]";
	}
}
