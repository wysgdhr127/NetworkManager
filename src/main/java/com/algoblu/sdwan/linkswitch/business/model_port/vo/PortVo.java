package  com.algoblu.sdwan.linkswitch.business.model_port.vo;

import com.algoblu.sdwan.linkswitch.basic.po.Port;

/**
 * @description 
 * @author  wang yang
 */
@SuppressWarnings("serial")
public class PortVo extends Port{

	//~ fields =======================================================
    private String nodeCode;
    private String location;
    private String bridgeCode;
    private String bridgeDesc;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}