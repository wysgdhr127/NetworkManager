package  com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.business.model_linkQualityReport.vo.LinkQualityReportVo;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface LinkQualityReportService extends ModelRoleService<LinkQualityReportVo>{

	
	//~ User-defined Method =======================================================
	public List<NodeVo> linkQualityReportNode();

}