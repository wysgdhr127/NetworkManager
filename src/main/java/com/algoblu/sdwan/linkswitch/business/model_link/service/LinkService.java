package  com.algoblu.sdwan.linkswitch.business.model_link.service;

import org.springframework.stereotype.Service;
import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface LinkService extends ModelRoleService<LinkVo>{

	//~ User-defined Method =======================================================
	public ErrorVo updateLinkInfo();
}