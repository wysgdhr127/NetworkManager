package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Policy extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
	







	{
		return this.id;
	}

	{
		this.id=id;
	}

	{
		return this.linkId;
	}

	{
		this.linkId=linkId;
	}

		return delayTime;
	}

	public void setDelayTime(Double delayTime) {
		this.delayTime = delayTime;
	}

	public Double getJitter() {
		return jitter;
	}

	public void setJitter(Double jitter) {
		this.jitter = jitter;
	}

	public Double getLossPackageRate() {
		return lossPackageRate;
	}

	public void setLossPackageRate(Double lossPackageRate) {
		this.lossPackageRate = lossPackageRate;
	}

	public Long getBackupLinkId()
	{
		return this.backupLinkId;
	}

	{
		this.backupLinkId=backupLinkId;
	}

	{
		return this.status;
	}

	{
		this.status=status;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", linkId=" + linkId + ", delayTime=" + delayTime + ", jitter=" + jitter
				+ ", lossPackageRate=" + lossPackageRate + ", backupLinkId=" + backupLinkId + ", status=" + status
				+ "]";
	}

}