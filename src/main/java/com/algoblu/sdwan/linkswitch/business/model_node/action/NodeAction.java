package com.algoblu.sdwan.linkswitch.business.model_node.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_node.service.NodeService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author wang yang
 */

@Controller("NodeAction")
@Scope("prototype")
public class NodeAction {

	// ~ fields
	// ===================================================================
	@Autowired
	private NodeService nodeService;
	private List<NodeVo> resultList = new ArrayList<>();
	private NodeVo vo;
	private NodeVo avo;// ajax搜索条件
	private NodeVo svo;// 搜索条件
	private ErrorVo error;

	// ~ Getters && Setters
	// =======================================================

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public List<NodeVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<NodeVo> resultList) {
		this.resultList = resultList;
	}

	public NodeVo getVo() {
		return vo;
	}

	public void setVo(NodeVo vo) {
		this.vo = vo;
	}

	public NodeVo getAvo() {
		return avo;
	}

	public void setAvo(NodeVo avo) {
		this.avo = avo;
	}

	public NodeVo getSvo() {
		return svo;
	}

	public void setSvo(NodeVo svo) {
		this.svo = svo;
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}

	// ~ Function
	// =================================================================
	/**
	 * [Node]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new NodeVo();
		}
		// 查询
		resultList = nodeService.searchList(avo, false);

		return "success";
	}

	/**
	 * [Node]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new NodeVo();
		}

		return "success";
	}

	/**
	 * [Node]搜索列表页面 搜索
	 * 
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new NodeVo();
		}
		// 查询
		resultList = nodeService.searchList(svo, true);
		return "success";

	}

	/**
	 * [Node]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new NodeVo();
		}

		return "success";
	}

	/**
	 * [Node]新增列表页面 增加
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
		error = nodeService.add(vo);

		return "success";
	}

	/**
	 * [Node]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new NodeVo();
		}
		vo = nodeService.searchById(vo.getId());

		return "success";
	}

	/**
	 * [Node]基本参数更新 修改
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
		error = nodeService.update(vo);

		return "success";
	}

	/**
	 * [Node]基本参数删除 修改
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
			error = nodeService.delete(vo.getId());
		}

		return "success";
	}

	// ~ User-defined Function
	// =======================================================

	/**
	 * [Node]基本参数更新 修改
	 * 
	 * @return
	 */
	public String updateNodeInfo() {
		error = nodeService.updateNodeInfo();
		return "success";
	}
}