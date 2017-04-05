package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class Policy extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private Long linkId;
	private Double delayTime;
	private Double jitter;
	private Double lossPackageRate;
	private Long backupLinkId;
	private String status;
	public Long getId()
	{
		return this.id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public Long getLinkId()
	{
		return this.linkId;
	}
	public void setLinkId(Long linkId)
	{
		this.linkId=linkId;
	}
	public Double getDelayTime() {
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
	public void setBackupLinkId(Long backupLinkId)
	{
		this.backupLinkId=backupLinkId;
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
		return "Policy [id=" + id + ", linkId=" + linkId + ", delayTime=" + delayTime + ", jitter=" + jitter
				+ ", lossPackageRate=" + lossPackageRate + ", backupLinkId=" + backupLinkId + ", status=" + status
				+ "]";
	}

}
