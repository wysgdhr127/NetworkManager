package com.algoblu.sdwan.linkswitch.business.model_policy.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_node.service.NodeService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import com.algoblu.sdwan.linkswitch.business.model_policy.service.PolicyService;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyVo;

/**
 * @author wang yang
 * @description
 */

@Controller("PolicyAction")
@Scope("prototype")
public class PolicyAction {

	// ~ fields
	// ===================================================================
	@Autowired
	private PolicyService policyService;
	private List<PolicyVo> resultList;
	private List<PolicyVo> policyVoList;
	private List<PolicyVo> policyVos;
	private PolicyVo vo;
	private PolicyVo avo;// ajax搜索条件
	private PolicyVo svo;// 搜索条件
	private ErrorVo error;

	@Autowired
	private NodeService nodeService;
	private List<NodeVo> nodeVoList;
	private NodeVo nodeVo;

	private String result;

	// ~ Getters && Setters
	// =======================================================

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public List<PolicyVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<PolicyVo> resultList) {
		this.resultList = resultList;
	}

	public PolicyVo getVo() {
		return vo;
	}

	public void setVo(PolicyVo vo) {
		this.vo = vo;
	}

	public PolicyVo getAvo() {
		return avo;
	}

	public void setAvo(PolicyVo avo) {
		this.avo = avo;
	}

	public PolicyVo getSvo() {
		return svo;
	}

	public void setSvo(PolicyVo svo) {
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

	public NodeVo getNodeVo() {
		return nodeVo;
	}

	public void setNodeVo(NodeVo nodeVo) {
		this.nodeVo = nodeVo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<NodeVo> getNodeVoList() {
		return nodeVoList;
	}

	public void setNodeVoList(List<NodeVo> nodeVoList) {
		this.nodeVoList = nodeVoList;
	}

	public List<PolicyVo> getPolicyVoList() {
		return policyVoList;
	}

	public void setPolicyVoList(List<PolicyVo> policyVoList) {
		this.policyVoList = policyVoList;
	}

	public List<PolicyVo> getPolicyVos() {
		return policyVos;
	}

	public void setPolicyVos(List<PolicyVo> policyVos) {
		this.policyVos = policyVos;
	}
	// ~ Function
	// =================================================================

	/**
	 * [Policy]搜索列表页面 进入
	 *
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new PolicyVo();
		}
		// 查询
		resultList = policyService.searchList(avo, false);
		return "success";
	}

	/**
	 * [Policy]搜索列表页面 进入初始化
	 *
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new PolicyVo();
		}
		return "success";
	}

	/**
	 * [Policy]搜索列表页面 进入初始化
	 *
	 * @return
	 */
	public String inConfig() {
		if (null == svo) {
			svo = new PolicyVo();
		}
		nodeVo = new NodeVo();
		nodeVo.setId(svo.getNodeId());
		nodeVo = nodeService.searchByNodeCode(nodeVo);
		nodeVoList = policyService.otherNodeList(nodeVo);
		return "success";
	}

	/**
	 * [Policy]搜索列表页面 搜索
	 *
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new PolicyVo();
		}
		resultList = policyService.searchList(svo, true);
		return "success";
	}

	/**
	 * [Policy]新增列表页面 进入
	 *
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new PolicyVo();
		}

		return "success";
	}

	/**
	 * [Policy]新增列表页面 增加
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
		error = policyService.add(vo);

		return "success";
	}

	/**
	 * [Policy]更新列表页面 进入
	 *
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new PolicyVo();
		}
		vo = policyService.searchById(vo.getId());

		return "success";
	}

	/**
	 * [Policy]基本参数更新 修改
	 *
	 * @return
	 */
	public String update() {
		if (policyVoList == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,update fail!");
			error.setErrorMessage("参数为空,更新失败!");
			return "success";
		}
		
		policyVos = new ArrayList<>();
		for (PolicyVo policyVo : policyVoList) {
			if (policyVo != null) {
				policyVos.add(policyVo);
			}
		}

		result = policyService.effectPolicy(policyVos);
		if (result.equals("success")) {
			for (PolicyVo policyVo : policyVos) {
				vo = new PolicyVo();
				vo = policyService.searchByLinkId(policyVo.getLinkId());
				if (null == vo.getId()) {
					policyVo.setStatus("valid");
					error = policyService.add(policyVo);
				} else {
					policyVo.setId(vo.getId());
					error = policyService.update(policyVo);
				}
			}
		}else if(result.equals("failed")){
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("RPC interface failed to call");
			error.setErrorMessage("RPC接口调用失败!");
			return "success";
		}
		return "success";
	}

	/**
	 * [Policy]基本参数删除 修改
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
			error = policyService.update(vo);
		}

		return "success";
	}

	// ~ User-defined Function
	// =======================================================

}