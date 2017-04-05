package com.algoblu.sdwan.linkswitch.business.model_node.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algoblu.sdwan.linkswitch.basic.Constants;
import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluNode;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluPort;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.basic.utils.HttpAuthTool;
import com.algoblu.sdwan.linkswitch.basic.utils.JsonUtils;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_node.service.NodeService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author wang yang
 * @description
 */
@Service
public class NodeServiceImpl extends BasicService implements NodeService {

	protected Logger logger = Logger.getLogger(this.getClass());
	// ~ fields =======================================================
	@Autowired
	private NodeDao nodeDao;

	// ~ Method =======================================================
	@Override
	public List<NodeVo> searchList(NodeVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Node> mList = null;

		if (isPage) {
			int findEntityListSize = nodeDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = nodeDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = nodeDao.find(vo);
		}

		List<NodeVo> resultVoList = new ArrayList<NodeVo>();
		for (Node node : mList) {
			NodeVo one = new NodeVo();
			this.copyProperties(one, node);
			resultVoList.add(one);
		}

		return resultVoList;
	}

	@Override
	public NodeVo searchById(Long id) {
		// TODO Auto-generated method stub
		NodeVo returnVo = new NodeVo();
		Node po = nodeDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(NodeVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error = new ErrorVo();
		try {
			System.out.println(vo.getStatus());
			if (null == vo.getStatus()) {
				vo.setStatus("valid");
			}
			int n = nodeDao.save(vo);
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
	public ErrorVo update(NodeVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Node po = new Node();
		this.copyProperties(po, vo);
		// find update
		Node bossUser = nodeDao.findUniqueBySingleParams("id", vo.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = nodeDao.update(po);
			if (n > 0) {
				this.copyProperties(vo, po);
				er.setSuccess(true);
				er.setErrorCode("save success!");
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
		ErrorVo er = new ErrorVo();

		Node node = nodeDao.findUniqueBySingleParams("id", String.valueOf(id));
		if (null == node) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! delete fail!");
			er.setErrorMessage("该记录不存在,删除失败");
			return er;
		}

		try {
			int n = nodeDao.delete(node);
			if (n > 0) {
				er.setSuccess(true);
				er.setErrorCode("save success!");
				er.setErrorMessage("删除成功,条数:" + n);
				er.setErrorMessage("删除成功");
			} else {
				er.setSuccess(false);
				er.setErrorCode("save fail![" + n + "]");
				er.setErrorMessage("删除失败");
			}
		} catch (Exception e) {
			er.setSuccess(false);
			er.setErrorCode("delete error!");
			er.setErrorMessage("删除出错");
			e.printStackTrace();
		}
		return er;
	}

	@Override
	public NodeVo searchByNodeCode(NodeVo vo) {
		NodeVo nodeVo = new NodeVo();
		Node node = nodeDao.findUniqueBySingleParams("id", String.valueOf(vo.getId()));
		if (null != node) {
			copyProperties(nodeVo, node);
		}
		return nodeVo;
	}
	// ~ User-defined Method
	// =======================================================

	@SuppressWarnings("rawtypes")
	@Override
	public ErrorVo updateNodeInfo() {
		// TODO Auto-generated method stub
		ErrorVo errorVo = new ErrorVo();
		
		List<Node> nodes = new ArrayList<>();
		nodes = nodeDao.find(null);

		for (Node node : nodes) {
			Map<String, String> systemConfigMap = SpringContextHolder.getBean("systemConfigMap");
			Map<String, String> urlContentMap = null;
			urlContentMap = new HashMap<String, String>();
			urlContentMap.put(Constants.APPLY_POLICY_NODEIP, node.getNodeIp());
			String URL = contentTemplate(urlContentMap, systemConfigMap.get(Constants.GET_ALGOBLU_NODE_URL_TEMPLATE));
			String jsonContent = "{input:{}}";
			String xmlstr = null;
			try {
				xmlstr = HttpAuthTool.postMethod(URL, jsonContent);
			} catch (Exception e) {
				// TODO: handle exception
				node.setStatus("invalid");
				nodeDao.update(node);
				continue;
			}
			JSONObject jsonObject = JSONObject.fromObject(xmlstr);
			JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("output").toString());
			JSONArray jsonArray = JSONArray.fromObject(jsonObject1.get("outNodes").toString());
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("portList", AlgobluPort.class);
			String algobluNodeName = "";
			for (Object object : jsonArray) {
				AlgobluNode algobluNode = (AlgobluNode) JsonUtils.jsonToObject(object.toString(), classMap,
						AlgobluNode.class);
				node.setNodeCode(algobluNode.getAlgobluNodeId());
				algobluNodeName += algobluNode.getAlgobluNodeName();
			}
			node.setNodeName(algobluNodeName);
			node.setStatus("valid");
			try {
				int n = nodeDao.update(node);
				if (n > 0) {
					errorVo.setSuccess(true);
					errorVo.setErrorCode("save success!");
					errorVo.setErrorMessage("更新节点成功");
				} else {
					errorVo.setSuccess(false);
					errorVo.setErrorCode("save fail![" + n + "]");
					errorVo.setErrorMessage("更新节点失败");
				}
			} catch (Exception e) {
				// TODO: handle exception
				errorVo.setSuccess(false);
				errorVo.setErrorCode("save error!");
				errorVo.setErrorMessage("更新节点出错");
			}
			
		}
		return errorVo;
	}
}