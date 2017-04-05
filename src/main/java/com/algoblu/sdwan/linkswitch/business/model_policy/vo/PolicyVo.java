package  com.algoblu.sdwan.linkswitch.business.model_policy.vo;

import java.io.Serializable;

import com.algoblu.sdwan.linkswitch.basic.po.Policy;

/**
 * @description 
 * @author  wang yang
 */
@SuppressWarnings("serial")
public class PolicyVo extends Policy implements Serializable{

	//~ fields =======================================================
	private Long nodeId;
	private String nodeIp;
	private String nodeCode;
	private String portIp;
	private String currentPort;
	private String backupPort;
	private String bridgeCode;
	private String linkName;
	private String backupLinkName;
	private String bridgeDesc;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getBackupLinkName() {
		return backupLinkName;
	}

	public void setBackupLinkName(String backupLinkName) {
		this.backupLinkName = backupLinkName;
	}

	public String getBridgeDesc() {
		return bridgeDesc;
	}

	public void setBridgeDesc(String bridgeDesc) {
		this.bridgeDesc = bridgeDesc;
	}

	public String getNodeIp() {
		return nodeIp;
	}

	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getBridgeCode() {
		return bridgeCode;
	}

	public void setBridgeCode(String bridgeCode) {
		this.bridgeCode = bridgeCode;
	}

	public String getPortIp() {
		return portIp;
	}

	public void setPortIp(String portIp) {
		this.portIp = portIp;
	}

	public String getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(String currentPort) {
		this.currentPort = currentPort;
	}

	public String getBackupPort() {
		return backupPort;
	}

	public void setBackupPort(String backupPort) {
		this.backupPort = backupPort;
	} 	
}