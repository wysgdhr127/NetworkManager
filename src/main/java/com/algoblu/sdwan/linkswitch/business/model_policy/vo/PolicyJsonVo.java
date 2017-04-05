package com.algoblu.sdwan.linkswitch.business.model_policy.vo;

import java.io.Serializable;
import java.util.List;

/** 
* @author 作者：王阳   E-mail:282404989@qq.com 
* @version 创建时间：16 Jan 2017 16:12:41 
* 类说明 
*/
@SuppressWarnings("serial")
public class PolicyJsonVo implements Serializable{
	
	private String applyLocation;
	private String currentLink;
	private String targetLocation;
	private List<PolicyJson> policyList;
	
	public String getApplyLocation() {
		return applyLocation;
	}
	public void setApplyLocation(String applyLocation) {
		this.applyLocation = applyLocation;
	}
	public String getCurrentLink() {
		return currentLink;
	}
	public void setCurrentLink(String currentLink) {
		this.currentLink = currentLink;
	}
	public String getTargetLocation() {
		return targetLocation;
	}
	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}
	public List<PolicyJson> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<PolicyJson> policyList) {
		this.policyList = policyList;
	}
}
