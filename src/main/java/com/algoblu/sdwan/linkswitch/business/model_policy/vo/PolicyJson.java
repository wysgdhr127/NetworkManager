package com.algoblu.sdwan.linkswitch.business.model_policy.vo;

import java.io.Serializable;

/** 
* @author 作者：王阳   E-mail:282404989@qq.com 
* @version 创建时间：16 Jan 2017 16:17:02 
* 类说明 
*/
@SuppressWarnings("serial")
public class PolicyJson implements Serializable{
	private String backupPort;
	private Double jitterLimit;
	private Double lossPacketRateLimit;
	private String policyLink;
	private Double delayLimit;
	private String portIP;
	public String getBackupPort() {
		return backupPort;
	}
	public void setBackupPort(String backupPort) {
		this.backupPort = backupPort;
	}
	
	public String getPolicyLink() {
		return policyLink;
	}
	public void setPolicyLink(String policyLink) {
		this.policyLink = policyLink;
	}
	
	public String getPortIP() {
		return portIP;
	}
	public void setPortIP(String portIP) {
		this.portIP = portIP;
	}
	public Double getJitterLimit() {
		return jitterLimit;
	}
	public void setJitterLimit(Double jitterLimit) {
		this.jitterLimit = jitterLimit;
	}
	public Double getLossPacketRateLimit() {
		return lossPacketRateLimit;
	}
	public void setLossPacketRateLimit(Double lossPacketRateLimit) {
		this.lossPacketRateLimit = lossPacketRateLimit;
	}
	public Double getDelayLimit() {
		return delayLimit;
	}
	public void setDelayLimit(Double delayLimit) {
		this.delayLimit = delayLimit;
	}
}
