package com.algoblu.sdwan.linkswitch.business.model_dashboard.vo;

import java.io.Serializable;

/** 
* @author 作者：王阳   E-mail:282404989@qq.com 
* @version 创建时间：10 Jan 2017 10:24:25 
* 类说明 
*/
@SuppressWarnings("serial")
public class LinksVo implements Serializable{
	private Long source;
	private Long target;
	private String type;
	private String targetIp;
	private Boolean isActive;
	
	
	public Long getSource() {
		return source;
	}
	public void setSource(Long source) {
		this.source = source;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "LinksVo [source=" + source + ", target=" + target + ", type=" + type + ", targetIp=" + targetIp
				+ ", isActive=" + isActive + "]";
	}
}
