package com.algoblu.sdwan.linkswitch.business.model_link.vo;

import com.algoblu.sdwan.linkswitch.basic.po.Link;
import com.algoblu.sdwan.linkswitch.basic.po.Policy;

/**
 * @description
 * @author wang yang
 */
@SuppressWarnings("serial")
public class LinkVo extends Link {

	// ~ fields =======================================================

	private String bridgeCode;
	private String bridgeDesc;
	private String currentPortCode;
	private Long currentPortNodeId;
	private String currentPortNodeCode;
	private String currentPortNodeIp;
	private String currentPortNodeLocation;
	private String targetPortCode;
	private Long targetPortNodeId;
	private String targetPortNodeCode;
	private String targetPortNodeLocation;
	private String portIP;

	private String nodeId;
	private String connectNodeName;
	
	private String bridgeId;
	
	private Long otherLink;
	private String otherLinkName;
	private String otherPortCode;
	private Long [] optLinkId;
	private String [] optLinkName;
	private String [] optPortCode;
	
	private Policy policy;

	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getConnectNodeName() {
		return connectNodeName;
	}

	public void setConnectNodeName(String connectNodeName) {
		this.connectNodeName = connectNodeName;
	}

	public String getBridgeCode() {
		return bridgeCode;
	}

	public void setBridgeCode(String bridgeCode) {
		this.bridgeCode = bridgeCode;
	}

	public String getBridgeDesc() {
		return bridgeDesc;
	}

	public void setBridgeDesc(String bridgeDesc) {
		this.bridgeDesc = bridgeDesc;
	}

	public String getCurrentPortCode() {
		return currentPortCode;
	}

	public void setCurrentPortCode(String currentPortCode) {
		this.currentPortCode = currentPortCode;
	}

	public String getCurrentPortNodeCode() {
		return currentPortNodeCode;
	}

	public void setCurrentPortNodeCode(String currentPortNodeCode) {
		this.currentPortNodeCode = currentPortNodeCode;
	}

	public String getCurrentPortNodeLocation() {
		return currentPortNodeLocation;
	}

	public void setCurrentPortNodeLocation(String currentPortNodeLocation) {
		this.currentPortNodeLocation = currentPortNodeLocation;
	}

	public String getTargetPortCode() {
		return targetPortCode;
	}

	public void setTargetPortCode(String targetPortCode) {
		this.targetPortCode = targetPortCode;
	}

	public String getTargetPortNodeCode() {
		return targetPortNodeCode;
	}

	public void setTargetPortNodeCode(String targetPortNodeCode) {
		this.targetPortNodeCode = targetPortNodeCode;
	}

	public String getTargetPortNodeLocation() {
		return targetPortNodeLocation;
	}

	public void setTargetPortNodeLocation(String targetPortNodeLocation) {
		this.targetPortNodeLocation = targetPortNodeLocation;
	}

	public String getPortIP() {
		return portIP;
	}

	public void setPortIP(String portIP) {
		this.portIP = portIP;
	}

	public Long getCurrentPortNodeId() {
		return currentPortNodeId;
	}

	public void setCurrentPortNodeId(Long currentPortNodeId) {
		this.currentPortNodeId = currentPortNodeId;
	}

	public Long getTargetPortNodeId() {
		return targetPortNodeId;
	}

	public void setTargetPortNodeId(Long targetPortNodeId) {
		this.targetPortNodeId = targetPortNodeId;
	}

	public Long[] getOptLinkId() {
		return optLinkId;
	}

	public void setOptLinkId(Long[] optLinkId) {
		this.optLinkId = optLinkId;
	}

	public String getBridgeId() {
		return bridgeId;
	}

	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	public Long getOtherLink() {
		return otherLink;
	}

	public void setOtherLink(Long otherLink) {
		this.otherLink = otherLink;
	}

	public String getOtherLinkName() {
		return otherLinkName;
	}

	public void setOtherLinkName(String otherLinkName) {
		this.otherLinkName = otherLinkName;
	}

	public String[] getOptLinkName() {
		return optLinkName;
	}

	public void setOptLinkName(String[] optLinkName) {
		this.optLinkName = optLinkName;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getCurrentPortNodeIp() {
		return currentPortNodeIp;
	}

	public void setCurrentPortNodeIp(String currentPortNodeIp) {
		this.currentPortNodeIp = currentPortNodeIp;
	}

	public String getOtherPortCode() {
		return otherPortCode;
	}

	public void setOtherPortCode(String otherPortCode) {
		this.otherPortCode = otherPortCode;
	}

	public String[] getOptPortCode() {
		return optPortCode;
	}

	public void setOptPortCode(String[] optPortCode) {
		this.optPortCode = optPortCode;
	}
	
	
	
}