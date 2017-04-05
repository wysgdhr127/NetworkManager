package com.algoblu.sdwan.linkswitch.business.model_link.service.impl;

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
import com.algoblu.sdwan.linkswitch.basic.po.Port;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluNode;
import com.algoblu.sdwan.linkswitch.basic.rpcEntity.AlgobluPort;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.basic.utils.HttpAuthTool;
import com.algoblu.sdwan.linkswitch.basic.utils.JsonUtils;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_link.service.LinkService;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;
import com.algoblu.sdwan.linkswitch.common.dao.LinkDao;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;
import com.algoblu.sdwan.linkswitch.common.dao.PortDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description
 * @author wang yang
 */
@Service
public class LinkServiceImpl extends BasicService implements LinkService {

	protected Logger logger = Logger.getLogger(this.getClass());
	// ~ fields =======================================================
	@Autowired
	private LinkDao linkDao;

	@Autowired
	private NodeDao nodeDao;

	@Autowired
	private PortDao portDao;

	// ~ Method =======================================================
	@Override
	public List<LinkVo> searchList(LinkVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Link> mList = null;

		if (isPage) {
			int findEntityListSize = linkDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = linkDao.find(vo);
			System.out.println(mList.toString());
		} else {
			vo.setPageDisable(true);
			mList = linkDao.find(vo);
			System.out.println(mList.toString());
		}

		List<LinkVo> resultVoList = new ArrayList<LinkVo>();
		for (Link link : mList) {
			LinkVo one = new LinkVo();
			this.copyProperties(one, link);
			resultVoList.add(one);
		}

		return resultVoList;
	}

	@Override
	public LinkVo searchById(Long id) {
		// TODO Auto-generated method stub
		LinkVo returnVo = new LinkVo();
		Link po = linkDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(LinkVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error = new ErrorVo();
		try {
			int n = linkDao.save(vo);
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
	public ErrorVo update(LinkVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Link po = new Link();
		this.copyProperties(po, vo);

		// find update
		Link bossUser = linkDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = linkDao.update(po);
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
	public ErrorVo updateLinkInfo() {
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
				if (null != algobluNode.getAlgobluSrcNodeName()) {
					Node targetnode = new Node();
					targetnode.setStatus("valid");
					targetnode.setNodeName(algobluNode.getAlgobluNodeName());
					targetnode = nodeDao.findUnique(targetnode);

					Node currentnode = new Node();
					currentnode.setNodeName(algobluNode.getAlgobluSrcNodeName());
					currentnode.setStatus("valid");
					currentnode = nodeDao.findUnique(currentnode);

					if (null != targetnode.getId() && null != currentnode.getId()) {
						for (AlgobluPort algobluPort : algobluNode.getPortList()) {
							if (algobluPort.getAlgobluSrcPortNum() != null
									&& !(algobluPort.getAlgobluPortNum().indexOf("LOCAL") > -1)) {
								Port targetport = new Port();
								targetport.setNodeId(targetnode.getId());
								targetport.setPortCode(algobluPort.getAlgobluSrcPortNum());
								targetport = portDao.findUnique(targetport);

								Port currentport = new Port();
								currentport.setNodeId(currentnode.getId());
								currentport.setPortCode(algobluPort.getAlgobluPortNum());
								currentport = portDao.findUnique(currentport);

								LinkVo linkVo = new LinkVo();
								linkVo.setCurrentPortId(currentport.getId());
								linkVo.setTargetPortId(targetport.getId());
								linkVo.setStatus("valid");
								linkVo.setIsActive(false);
								Link link = new Link();
								link = linkDao.findUnique(linkVo);
								if (null == link) {
									if (algobluPort.getAlgobluPortNum().equals("2")) {
										linkVo.setLinkName("main");
									}else if (algobluPort.getAlgobluPortNum().equals("3")) {
										linkVo.setLinkName("backup");
									}else if (algobluPort.getAlgobluPortNum().equals("4")){
										linkVo.setLinkName("sdn");
									}
									linkDao.save(linkVo);
								}else {
									if (algobluPort.getAlgobluPortNum().equals("2")) {
										linkVo.setLinkName("main");
									}else if (algobluPort.getAlgobluPortNum().equals("3")) {
										linkVo.setLinkName("backup");
									}else if (algobluPort.getAlgobluPortNum().equals("4")){
										linkVo.setLinkName("sdn");
									}
									linkVo.setId(link.getId());
									linkDao.update(linkVo);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}