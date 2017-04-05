package  com.algoblu.sdwan.linkswitch.business.model_policy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.business.model_node.vo.NodeVo;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface PolicyService extends ModelRoleService<PolicyVo>{	

	//~ User-defined Method =======================================================
	public List<NodeVo> otherNodeList(NodeVo nodeVo);

	public PolicyVo searchByLinkId(Long id);
	
	public String effectPolicy(List<PolicyVo> policyVos);
}