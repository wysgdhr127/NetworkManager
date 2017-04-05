package com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.service.LinkQualityReportService;
import com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.vo.LinkQualityReportVo;
import com.algoblu.sdwan.linkswitch.business.model_node.service.NodeService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;

/**
 * @description
 * @author wang yang
 */

@Controller("LinkQualityReportAction")
@Scope("prototype")
public class LinkQualityReportAction {

	// ~ fields
	// ===================================================================
	@Autowired
	private LinkQualityReportService linkQualityReportService;
	private List<LinkQualityReportVo> resultList;
	private LinkQualityReportVo vo;
	private LinkQualityReportVo avo;// ajax搜索条件
	private LinkQualityReportVo svo;// 搜索条件
	private ErrorVo error;

	@Autowired
	private NodeService nodeService;
	private List<NodeVo> nodeVos;

	// ~ Getters && Setters
	// =======================================================

	public LinkQualityReportService getLinkQualityReportService() {
		return linkQualityReportService;
	}

	public void setLinkQualityReportService(LinkQualityReportService linkQualityReportService) {
		this.linkQualityReportService = linkQualityReportService;
	}

	public List<LinkQualityReportVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<LinkQualityReportVo> resultList) {
		this.resultList = resultList;
	}

	public LinkQualityReportVo getVo() {
		return vo;
	}

	public void setVo(LinkQualityReportVo vo) {
		this.vo = vo;
	}

	public LinkQualityReportVo getAvo() {
		return avo;
	}

	public void setAvo(LinkQualityReportVo avo) {
		this.avo = avo;
	}

	public LinkQualityReportVo getSvo() {
		return svo;
	}

	public void setSvo(LinkQualityReportVo svo) {
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

	public List<NodeVo> getNodeVos() {
		return nodeVos;
	}

	public void setNodeVos(List<NodeVo> nodeVos) {
		this.nodeVos = nodeVos;
	}

	// ~ Function
	// =================================================================
	/**
	 * [LinkQualityRepor]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new LinkQualityReportVo();
		}
		// 查询
		resultList = linkQualityReportService.searchList(avo, false);

		return "success";
	}

	/**
	 * [LinkQualityRepor]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new LinkQualityReportVo();
		}

		return "success";
	}

	/**
	 * [LinkQualityRepor]搜索列表页面 搜索
	 * 
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new LinkQualityReportVo();
		}
		// 查询
		nodeVos = linkQualityReportService.linkQualityReportNode();
		System.out.println(nodeVos.toString());

		return "success";
	}

	/**
	 * [LinkQualityRepor]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new LinkQualityReportVo();
		}

		return "success";
	}

	/**
	 * [LinkQualityRepor]新增列表页面 增加
	 * 
	 * @return
	 */
	public String add() {
		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,save fail!");
			error.setErrorMessage("参数为空,保存失败!");
			return "success";
		}
		error = linkQualityReportService.add(vo);

		return "success";
	}

	/**
	 * [LinkQualityRepor]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new LinkQualityReportVo();
		}
		vo = linkQualityReportService.searchById(vo.getId());

		return "success";
	}

	/**
	 * [LinkQualityRepor]基本参数更新 修改
	 * 
	 * @return
	 */
	public String update() {
		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,update fail!");
			error.setErrorMessage("参数为空,更新失败!");
			return "success";
		}
		error = linkQualityReportService.update(vo);

		return "success";
	}

	/**
	 * [LinkQualityRepor]基本参数删除 修改
	 * 
	 * @return
	 */
	public String delete() {

		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,update fail!");
			error.setErrorMessage("参数为空,更新失败!");
			return "success";
		}

		if (null != vo.getId()) {
			error = linkQualityReportService.update(vo);
		}

		return "success";
	}

	// ~ User-defined Function
	// =======================================================

	/**
	 * [LinkQualityRepor]实时报告
	 * 
	 * @return
	 */
	public String realTimeReport() {
		if (null == svo) {
			svo = new LinkQualityReportVo();
		}
		// 查询
		resultList = linkQualityReportService.searchList(svo,false);

		return "success";
	}
	
	
	/**
	 * [LinkQualityRepor]历史报告
	 * 
	 * @return
	 */
	public String historyReport() {
		if (null == svo) {
			svo = new LinkQualityReportVo();
		}
		// 查询
		resultList = linkQualityReportService.searchList(svo,false);
		
		return "success";
	}
}