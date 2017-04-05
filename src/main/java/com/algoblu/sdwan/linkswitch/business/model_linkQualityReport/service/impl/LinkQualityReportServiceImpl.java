package  com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algoblu.sdwan.linkswitch.basic.po.Link;
import com.algoblu.sdwan.linkswitch.basic.po.LinkQualityReport;
import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.basic.po.Port;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;
import com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.service.LinkQualityReportService;
import com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.vo.LinkQualityReportVo;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import com.algoblu.sdwan.linkswitch.common.dao.LinkDao;
import com.algoblu.sdwan.linkswitch.common.dao.LinkQualityReportDao;
import com.algoblu.sdwan.linkswitch.common.dao.NodeDao;
import com.algoblu.sdwan.linkswitch.common.dao.PortDao;

/**
 * @description 
 * @author  wang yang
 */
@Service
public class LinkQualityReportServiceImpl  extends BasicService implements LinkQualityReportService{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	//~ fields =======================================================
	@Autowired
	private LinkQualityReportDao linkQualityReportDao;
	@Autowired
	private NodeDao nodeDao;
	@Autowired
	private LinkDao linkDao;
	@Autowired
	private PortDao portDao;
	
	//~ Method =======================================================
	@Override
	public List<LinkQualityReportVo> searchList(LinkQualityReportVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<LinkQualityReport> mList = null;

		if (isPage) {
			int findEntityListSize = linkQualityReportDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = linkQualityReportDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = linkQualityReportDao.find(vo);
		}

		List<LinkQualityReportVo> resultVoList = new ArrayList<LinkQualityReportVo>();
		for (LinkQualityReport linkQualityReport : mList) {
			LinkQualityReportVo one = new LinkQualityReportVo();
			this.copyProperties(one, linkQualityReport);
			resultVoList.add(one);
		}

		return resultVoList;
	}
	
	@Override
	public LinkQualityReportVo searchById(Long id) {
		// TODO Auto-generated method stub
		LinkQualityReportVo returnVo = new LinkQualityReportVo();
		LinkQualityReport po = linkQualityReportDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(LinkQualityReportVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error =new ErrorVo();
		try {
			int n = linkQualityReportDao.save(vo);
			if (n>0) {
				error.setSuccess(true);
				error.setErrorCode("save success!");
				error.setErrorMessage("保存成功,条数:" + n);
				error.setErrorMessage("保存成功");
			}else {
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
	public ErrorVo update(LinkQualityReportVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		LinkQualityReport po = new LinkQualityReport();
		this.copyProperties(po, vo);

		// find update
		LinkQualityReport bossUser = linkQualityReportDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = linkQualityReportDao.update(po);
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
	//~ User-defined Method =======================================================

	@Override
	public List<NodeVo> linkQualityReportNode() {
		// TODO Auto-generated method stub
		List<NodeVo> resultList = new ArrayList<>();
		List<Node> nodes = new ArrayList<>();
		List<Port> ports = new ArrayList<>();
		List<LinkVo> links;
		
		nodes = nodeDao.find(null);
		for (Node node : nodes) {
			links = new ArrayList<>();
			NodeVo nodeVo = new NodeVo();
			Port port = new Port();
			port.setNodeId(node.getId());
			ports = portDao.find(port);
			for (Port port2 : ports) {
				LinkVo linkVo = new LinkVo();
				Link link2 = new Link();
				linkVo.setCurrentPortId(port2.getId());
				link2 = linkDao.findUnique(linkVo);
				copyProperties(linkVo, link2);
				links.add(linkVo);
			}		
			copyProperties(nodeVo, node);
			nodeVo.setLinks(links);
			resultList.add(nodeVo);
		}
		
		return resultList;
	}
}