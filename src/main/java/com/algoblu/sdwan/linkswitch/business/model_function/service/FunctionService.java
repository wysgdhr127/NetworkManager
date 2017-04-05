package  com.algoblu.sdwan.linkswitch.business.model_function.service;

import com.algoblu.sdwan.linkswitch.basic.po.Function;
import com.algoblu.sdwan.linkswitch.basic.service.ModelRoleService;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
@Service
public interface FunctionService extends ModelRoleService<FunctionVo>{

	//~ User-defined Method =======================================================
    public List<Function> searchFunctionList(FunctionVo vo);

    public long findMaxId(FunctionVo vo);
}