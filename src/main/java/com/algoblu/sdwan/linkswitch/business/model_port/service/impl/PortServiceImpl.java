package com.algoblu.sdwan.linkswitch.business.model_port.service.impl;

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
import com.algoblu.sdwan.linkswitch.basic.po.Port;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluNode;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluPort;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.basic.utils.HttpAuthTool;
import com.algoblu.sdwan.linkswitch.basic.utils.JsonUtils;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_port.service.PortService;
import com.algoblu.sdwan.linkswitch.business.model_port.vo.PortVo;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;
import com.algoblu.sdwan.linkswitch.common.dao.PortDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description
 * @author wang yang
 */
@Service
public class PortServiceImpl extends BasicService implements PortService {

	protected Logger logger = Logger.getLogger(this.getClass());
	// ~ fields =======================================================
	@Autowired
	private PortDao portDao;

	@Autowired
	private NodeDao nodeDao;

	// ~ Method =======================================================
	@Override
	public List<PortVo> searchList(PortVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Port> mList = new ArrayList<>();

		if (isPage) {
			int findEntityListSize = portDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = portDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = portDao.find(vo);
		}

		List<PortVo> resultVoList = new ArrayList<PortVo>();
		for (Port port : mList) {
			PortVo one = new PortVo();
			this.copyProperties(one, port);
			resultVoList.add(one);
		}

		return resultVoList;
	}

	@Override
	public PortVo searchById(Long id) {
		// TODO Auto-generated method stub
		PortVo returnVo = new PortVo();
		Port po = portDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(PortVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error = new ErrorVo();
		try {
			if (vo.getStatus().isEmpty()) {
				vo.setStatus("valid");
			}
			int n = portDao.save(vo);
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
	public ErrorVo update(PortVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Port po = new Port();
		this.copyProperties(po, vo);

		// find update
		Port bossUser = portDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = portDao.update(po);
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

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo updatePortInfo() {
		// TODO Auto-generated method stub
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
				System.out.println(xmlstr);
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			JSONObject jsonObject = JSONObject.fromObject(xmlstr);
			JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("output").toString());
			JSONArray jsonArray = JSONArray.fromObject(jsonObject1.get("outNodes").toString());
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("portList", AlgobluPort.class);
			for (Object object : jsonArray) {
				AlgobluNode algobluNode = (AlgobluNode) JsonUtils.jsonToObject(object.toString(), classMap,
						AlgobluNode.class);
				for (AlgobluPort algobluPort : algobluNode.getPortList()) {
					if (!(algobluPort.getAlgobluPortNum().indexOf("LOCAL") > -1)) {
						Port port = new Port();
						port.setNodeId(node.getId());
						port.setMacAddress(algobluPort.getPhysicalAddress());
						Port uniquePort = new Port();
						uniquePort = portDao.findUnique(port);
						if (null == uniquePort) {
							port.setBridgeId(node.getId());
							port.setPortCode(algobluPort.getAlgobluPortNum());
							port.setPortName(algobluPort.getAlgobluPortName());
							port.setStatus("valid");
							portDao.save(port);
						} else {
							port.setId(uniquePort.getId());
							port.setBridgeId(node.getId());
							port.setPortCode(algobluPort.getAlgobluPortNum());
							port.setPortName(algobluPort.getAlgobluPortName());
							port.setStatus("valid");
							portDao.update(port);
						}
					}
				}
			}
		}
		return null;
	}
}