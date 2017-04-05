package com.algoblu.sdwan.linkswitch.business.model_dashboard.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.service.DashboardService;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.DashboardVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.LinksVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.NodesVo;
import com.algoblu.sdwan.linkswitch.business.model_node.service.NodeService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;

/**
 * @description
 * @author wang yang
 */

@Controller("DashboardAction")
@Scope("prototype")
public class DashboardAction {

	// ~ fields
	// ===================================================================
	@Autowired
	private DashboardService dashboardService;
	private List<DashboardVo> resultList;
	private DashboardVo vo;
	private DashboardVo avo;// ajax搜索条件
	private DashboardVo svo;// 搜索条件

	@Autowired
	private NodeService nodeService;
	private List<NodeVo> nodeVoList;
	private NodeVo nodeVo;
	private ErrorVo error;
	
	private List<NodesVo> nodes;
	private List<LinksVo> allLinks;
	private List<LinksVo> activelinks;
	
	private Map<String, Object> resultMsg;

	// ~ Getters && Setters
	// =======================================================

	public DashboardService getDashboardService() {
		return dashboardService;
	}

	public void setDashboardService(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	public List<DashboardVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<DashboardVo> resultList) {
		this.resultList = resultList;
	}

	public DashboardVo getVo() {
		return vo;
	}

	public void setVo(DashboardVo vo) {
		this.vo = vo;
	}

	public DashboardVo getAvo() {
		return avo;
	}

	public void setAvo(DashboardVo avo) {
		this.avo = avo;
	}

	public DashboardVo getSvo() {
		return svo;
	}

	public void setSvo(DashboardVo svo) {
		this.svo = svo;
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public List<NodeVo> getNodeVoList() {
		return nodeVoList;
	}

	public void setNodeVoList(List<NodeVo> nodeVoList) {
		this.nodeVoList = nodeVoList;
	}

	public NodeVo getNodeVo() {
		return nodeVo;
	}

	public void setNodeVo(NodeVo nodeVo) {
		this.nodeVo = nodeVo;
	}

	public List<NodesVo> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodesVo> nodes) {
		this.nodes = nodes;
	}

	public List<LinksVo> getAllLinks() {
		return allLinks;
	}

	public void setAllLinks(List<LinksVo> allLinks) {
		this.allLinks = allLinks;
	}

	public List<LinksVo> getActivelinks() {
		return activelinks;
	}

	public void setActivelinks(List<LinksVo> activelinks) {
		this.activelinks = activelinks;
	}

	public Map<String, Object> getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(Map<String, Object> resultMsg) {
		this.resultMsg = resultMsg;
	}

	// ~ Dashboard
	// =================================================================
	/**
	 * [Dashboard]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == svo) {
			svo = new DashboardVo();
		}
		nodes = dashboardService.dashboardNodes(svo);
		allLinks = dashboardService.dashboardAllLinks(svo);
		activelinks = dashboardService.dashboardActiveLinks(svo);
		resultMsg = new HashMap<String, Object>();
		
		resultMsg.put("node", nodes);
		resultMsg.put("link", activelinks);
		resultMsg.put("allLinks", allLinks);
		
		return "success";
	}

	/**
	 * [Dashboard]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new DashboardVo();
		}
		return "success";
	}

	/**
	 * [Dashboard]搜索列表页面 搜索
	 * 
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new DashboardVo();
		}
		nodes = dashboardService.dashboardNodes(svo);
		return "success";

	}

	// ~ User-defined Dashboard
	// =======================================================

}