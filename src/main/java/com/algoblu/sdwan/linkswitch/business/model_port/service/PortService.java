package  com.algoblu.sdwan.linkswitch.business.model_port.service;

import org.springframework.stereotype.Service;
import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_port.vo.PortVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface PortService extends ModelRoleService<PortVo>{

	

	//~ User-defined Method =======================================================
	ErrorVo updatePortInfo();
}