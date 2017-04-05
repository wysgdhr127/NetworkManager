package  com.algoblu.sdwan.linkswitch.business.model_node.service;

import org.springframework.stereotype.Service;

import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface NodeService extends ModelRoleService<NodeVo>{


    //~ User-defined Method =======================================================
    public NodeVo searchByNodeCode(NodeVo vo);

	public ErrorVo updateNodeInfo();
	
}