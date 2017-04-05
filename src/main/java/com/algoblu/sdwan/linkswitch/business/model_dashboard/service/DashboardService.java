package com.algoblu.sdwan.linkswitch.business.model_dashboard.service;

import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.DashboardVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.LinksVo;
import com.algoblu.sdwan.linkswitch.business.model_dashboard.vo.NodesVo;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface DashboardService extends ModelRoleService<DashboardVo> {

    //~ User-defined Method =======================================================

	public List<NodesVo> dashboardNodes(DashboardVo vo);

	public List<LinksVo> dashboardAllLinks(DashboardVo svo);

	public List<LinksVo> dashboardActiveLinks(DashboardVo svo);
}