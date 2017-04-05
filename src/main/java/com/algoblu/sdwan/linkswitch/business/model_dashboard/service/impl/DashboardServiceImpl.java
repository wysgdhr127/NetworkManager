package com.algoblu.sdwan.linkswitch.business.model_dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algoblu.sdwan.linkswitch.basic.po.Link;
import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.basic.po.Port;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.service.DashboardService;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.DashboardVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.LinksVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.NodesVo;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;
import com.algoblu.sdwan.linkswitch.business.model_port.vo.PortVo;
import com.algoblu.sdwan.linkswitch.common.dao.LinkDao;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;
import com.algoblu.sdwan.linkswitch.common.dao.PortDao;

/**
 * @description
 * @author wang yang
 */
@Service
public class DashboardServiceImpl extends BasicService implements DashboardService {

	protected Logger logger = Logger.getLogger(this.getClass());
	// ~ fields =======================================================

	@Autowired
	private NodeDao nodeDao;
	@Autowired
	private LinkDao linkDao;
	@Autowired
	private PortDao portDao;

	// ~ Method =======================================================
	@Override
	public List<DashboardVo> searchList(DashboardVo po, boolean isPage) {
		return null;
	}

	@Override
	public DashboardVo searchById(Long id) {
		return null;
	}

	@Override
	public ErrorVo add(DashboardVo po) {
		return null;
	}

	@Override
	public ErrorVo update(DashboardVo po) {
		return null;
	}

	@Override
	public ErrorVo delete(Long id) {
		return null;
	}

	@Override
	public List<NodesVo> dashboardNodes(DashboardVo vo) {
		// TODO Auto-generated method stub
		List<NodesVo> resultlist = new ArrayList<>();
		List<Node> nList = new ArrayList<>();
		List<Port> pList = new ArrayList<>();
		List<PortVo> portVos = null;

		Node validnode = new Node();
		validnode.setStatus("valid");
		nList = nodeDao.find(validnode);
		pList = portDao.find(null);

		for (Node node : nList) {
			NodesVo nodesVo = new NodesVo();
			copyProperties(nodesVo, node);
			portVos = new ArrayList<>();
			for (Port port : pList) {
				if (port.getNodeId().equals(nodesVo.getId())) {
					PortVo portVo = new PortVo();
					copyProperties(portVo, port);
					portVos.add(portVo);
				}
				nodesVo.setPortVos(portVos);
			}
			resultlist.add(nodesVo);
		}
		return resultlist;
	}

	@Override
	public List<LinksVo> dashboardActiveLinks(DashboardVo svo) {
		// TODO Auto-generated method stub
		List<LinksVo> resultlist = new ArrayList<>();
		List<Link> nList = new ArrayList<>();

		LinkVo linkVo = new LinkVo();
		linkVo.setPageDisable(true);
		nList = linkDao.find(linkVo);

		for (Link link : nList) {
			if (link.getIsActive() == true) {
				linkVo = new LinkVo();
				copyProperties(linkVo, link);
				LinksVo linksVo = new LinksVo();
				linksVo.setSource(linkVo.getCurrentPortNodeId());
				linksVo.setTarget(linkVo.getTargetPortNodeId());
				linksVo.setType(linkVo.getLinkName());
				linksVo.setIsActive(linkVo.getIsActive());
				linksVo.setTargetIp(linkVo.getPortIP());
				resultlist.add(linksVo);
			}
		}
		System.out.println(resultlist);
		return resultlist;
	}

	@Override
	public List<LinksVo> dashboardAllLinks(DashboardVo svo) {
		// TODO Auto-generated method stub
		List<LinksVo> resultlist = new ArrayList<>();
		List<Link> nList = new ArrayList<>();

		LinkVo linkVo = new LinkVo();
		linkVo.setPageDisable(true);
		nList = linkDao.find(linkVo);

		for (Link link : nList) {
			linkVo = new LinkVo();
			copyProperties(linkVo, link);
			LinksVo linksVo = new LinksVo();
			linksVo.setSource(linkVo.getCurrentPortNodeId() - 1);
			linksVo.setTarget(linkVo.getTargetPortNodeId() - 1);
			linksVo.setType(linkVo.getLinkName());
			linksVo.setIsActive(linkVo.getIsActive());
			linksVo.setTargetIp(linkVo.getPortIP());
			resultlist.add(linksVo);
		}
		return resultlist;
	}
}