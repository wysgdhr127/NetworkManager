package com.algoblu.sdwan.linkswitch.business.model_dashboard.vo;

import java.io.Serializable;
import java.util.List;

import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.business.model_port.vo.PortVo;

/** 
* @author 作者：王阳   E-mail:282404989@qq.com 
* @version 创建时间：10 Jan 2017 10:21:20 
* 类说明 
*/
@SuppressWarnings("serial")
public class NodesVo extends Node implements Serializable {
	

	private List<PortVo> portVos;
	
	public List<PortVo> getPortVos() {
		return portVos;
	}
	public void setPortVos(List<PortVo> portVos) {
		this.portVos = portVos;
	}
}
