package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Link extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
	






	{
		return this.id;
	}

	{
		this.id=id;
	}

	{
		return this.currentPortId;
	}

	{
		this.currentPortId=currentPortId;
	}

	{
		return this.targetPortId;
	}

	{
		this.targetPortId=targetPortId;
	}

	{
		return this.linkName;
	}

	{
		this.linkName=linkName;
	}

		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStatus()
	{
		return this.status;
	}

	{
		this.status=status;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", currentPortId=" + currentPortId + ", targetPortId=" + targetPortId + ", linkName="
				+ linkName + ", isActive=" + isActive + ", status=" + status + "]";
	}
}