package com.algoblu.sdwan.linkswitch.business.model_policy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algoblu.sdwan.linkswitch.basic.Constants;
import com.algoblu.sdwan.linkswitch.basic.po.Link;
import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.basic.po.Policy;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.basic.utils.HttpAuthTool;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import com.algoblu.sdwan.linkswitch.business.model_policy.service.PolicyService;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyJson;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyJsonVo;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyVo;
import com.algoblu.sdwan.linkswitch.common.dao.LinkDao;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;
import com.algoblu.sdwan.linkswitch.common.dao.PolicyDao;

import net.sf.json.JSONArray;

/**
 * @description
 * @author wang yang
 */
@Service
public class PolicyServiceImpl extends BasicService implements PolicyService {

	protected Logger logger = Logger.getLogger(this.getClass());
	// ~ fields =======================================================
	@Autowired
	private PolicyDao policyDao;

	@Autowired
	private NodeDao nodeDao;

	@Autowired
	private LinkDao linkDao;

	// ~ Method =======================================================
	@Override
	public List<PolicyVo> searchList(PolicyVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Policy> mList = null;

		if (isPage) {
			int findEntityListSize = policyDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = policyDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = policyDao.find(vo);
		}

		List<PolicyVo> resultVoList = new ArrayList<PolicyVo>();
		for (Policy policy : mList) {
			PolicyVo one = new PolicyVo();
			this.copyProperties(one, policy);
			resultVoList.add(one);
		}

		return resultVoList;
	}

	@Override
	public PolicyVo searchById(Long id) {
		// TODO Auto-generated method stub
		PolicyVo returnVo = new PolicyVo();
		Policy po = policyDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(PolicyVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error = new ErrorVo();
		try {
			int n = policyDao.save(vo);
			if (n > 0) {
				error.setSuccess(true);
				error.setErrorCode("save success!");
				error.setErrorMessage("保存成功,条数:" + n);
				error.setErrorMessage("保存成功");
			} else {
				error.setSuccess(false);
				error.setErrorCode("save fail!");
				error.setErrorMessage("保存出错");
			}
		} catch (Exception e) {
			// TODO: handle exception
			error.setSuccess(false);
			error.setErrorCode("save fail!");
			error.setErrorMessage("保存出错");
			e.printStackTrace();
		}

		return error;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo update(PolicyVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Policy po = new Policy();
		this.copyProperties(po, vo);

		// find update
		Policy bossUser = policyDao.findUniqueBySingleParams("linkId", po.getLinkId().toString());

		boolean isUpdate = !equalsWithPo(bossUser, po, "status");
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		if (!isUpdate) {
			er.setSuccess(false);
			er.setErrorCode("the record has no content been updated! update cancel!");
			er.setErrorMessage("没有信息被修改,不进行更新");
			return er;
		}

		// sql operate
		try {
			int n = policyDao.update(po);
			if (n > 0) {
				this.copyProperties(vo, po);
				er.setSuccess(true);
				er.setErrorCode("save success!");
				er.setErrorMessage("更新成功,条数:" + n);
				er.setErrorMessage("更新成功");
			} else {
				er.setSuccess(false);
				er.setErrorCode("save fail![" + n + "]");
				er.setErrorMessage("更新失败");
			}
		} catch (Exception e) {
			er.setSuccess(false);
			er.setErrorCode("update fail!");
			er.setErrorMessage("更新出错");
			e.printStackTrace();
		}

		return er;
	}

	@Override
	public ErrorVo delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	// ~ User-defined Method
	// =======================================================

	@Override
	public PolicyVo searchByLinkId(Long id) {
		// TODO Auto-generated method stub
		PolicyVo returnVo = new PolicyVo();
		Policy po = new Policy();
		po = policyDao.findUniqueBySingleParams("linkId", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	public List<NodeVo> otherNodeList(NodeVo nodeVo) {
		// TODO Auto-generated method stub
		List<NodeVo> nList = new ArrayList<>();
		List<Node> nodes = new ArrayList<>();
		List<Link> links = new ArrayList<>();

		links = linkDao.find(null);
		nodes = nodeDao.find(null);
		for (Node node : nodes) {
			NodeVo vo = new NodeVo();
			copyProperties(vo, node);
			if (!vo.getId().equals(nodeVo.getId())&&vo.getStatus().equals("valid")) {
				List<LinkVo> linklist = new ArrayList<>();
				for (Link link : links) {
					LinkVo linkVo = new LinkVo();
					copyProperties(linkVo, link);
					if (nodeVo.getId().equals(linkVo.getCurrentPortNodeId())
							&& vo.getId().equals(linkVo.getTargetPortNodeId())) {
						List<Link> links2 = new ArrayList<>();
						links2 = linkDao.find("otherlink", linkVo);
						Long[] optLinkId = new Long[3];
						String[] optLinkName = new String[3];
						String[] optPortCode = new String[3];
						int num = 0;
						for (Link link2 : links2) {
							LinkVo linkVo2 = new LinkVo();
							copyProperties(linkVo2, link2);
							optLinkId[num] = linkVo2.getOtherLink();
							optLinkName[num] = linkVo2.getOtherLinkName();
							optPortCode[num] = linkVo2.getOtherPortCode();
							num++;
						}
						Policy policy = new Policy();
						policy = policyDao.findUniqueBySingleParams("linkId", linkVo.getId().toString());
						linkVo.setPolicy(policy);
						linkVo.setOptLinkId(optLinkId);
						linkVo.setOptLinkName(optLinkName);
						linkVo.setOptPortCode(optPortCode);
						linklist.add(linkVo);
						vo.setLinks(linklist);
					}
				}
				nList.add(vo);
			}
		}
		return nList;
	}

	@Override
	public String effectPolicy(List<PolicyVo> policyVos) {
		// TODO Auto-generated method stub
		PolicyVo pVo = null;
		List<PolicyJson> policyList = new ArrayList<>();

		for (PolicyVo policyVo : policyVos) {
			if (null != policyVo) {
				pVo = new PolicyVo();
				PolicyJson policyJson = new PolicyJson();
				pVo.setLinkId(policyVo.getLinkId());
				Link link = linkDao.findUniqueBySingleParams("id", policyVo.getLinkId().toString());
				LinkVo linkVo = new LinkVo();
				copyProperties(linkVo, link);
				policyJson.setPortIP(linkVo.getPortIP());
				policyJson.setBackupPort((policyVo.getBackupLinkId()) + "");
				policyJson.setPolicyLink(linkVo.getCurrentPortCode());
				policyJson.setDelayLimit(policyVo.getDelayTime());
				policyJson.setLossPacketRateLimit(policyVo.getLossPackageRate());
				policyJson.setJitterLimit(policyVo.getJitter());
				policyList.add(policyJson);
			}
		}
		Link link = linkDao.findUniqueBySingleParams("id", pVo.getLinkId().toString());
		LinkVo linkVo = new LinkVo();
		copyProperties(linkVo, link);
		PolicyJsonVo policyJsonVo = new PolicyJsonVo();
		policyJsonVo.setApplyLocation(linkVo.getCurrentPortNodeCode());
		policyJsonVo.setCurrentLink("2");
		policyJsonVo.setPolicyList(policyList);
		policyJsonVo.setTargetLocation(linkVo.getBridgeCode());

		Map<String, String> systemConfigMap = SpringContextHolder.getBean("systemConfigMap");
		Map<String, String> urlContentMap = new HashMap<String, String>();
		urlContentMap.put(Constants.APPLY_POLICY_NODEIP, linkVo.getCurrentPortNodeIp());
		String URL = contentTemplate(urlContentMap, systemConfigMap.get(Constants.ADD_ALGOBLU_POLICY_URL_TEMPLATE));
		JSONArray jsonObject = JSONArray.fromObject(policyJsonVo);
		String jsonContent = "{input: " + jsonObject.get(0).toString() + "}";
		try {
			String xmlstr = HttpAuthTool.postMethod(URL, jsonContent);
		} catch (Exception e) {
			// TODO: handle exception
			return "failed";
		}
		return "success";
	}
}