package com.algoblu.sdwan.linkswitch.basic.po;

import java.io.Serializable;


// Generated 2016-2-23 16:41:21 by Hibernate Tools 3.4.0.CR1
@SuppressWarnings("serial")
public class LinkQualityReport extends Base_Bpo implements Serializable {
	
	//~ fields =======================================================
		private Long id;
	private Long linkId;
	private String delay;
	private String jitter;
	private String losspacket;
	private Integer datatime;
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
	public String getDelay()
	{
		return this.delay;
	}
	public void setDelay(String delay)
	{
		this.delay=delay;
	}
	public String getJitter()
	{
		return this.jitter;
	}
	public void setJitter(String jitter)
	{
		this.jitter=jitter;
	}
	public String getLosspacket()
	{
		return this.losspacket;
	}
	public void setLosspacket(String losspacket)
	{
		this.losspacket=losspacket;
	}
	public Integer getDatatime()
	{
		return this.datatime;
	}
	public void setDatatime(Integer datatime)
	{
		this.datatime=datatime;
	}

}
