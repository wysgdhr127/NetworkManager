package com.algoblu.sdwan.linkswitch.common.dao;

import com.algoblu.sdwan.linkswitch.basic.dao.GenericDao;
import com.algoblu.sdwan.linkswitch.basic.po.Function;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;

import java.util.List;

/**
 * 
 * @description
 *
 * @author  wang yang
 */
public interface FunctionDao extends GenericDao<Function> {

    public List<Function> selectFunction(FunctionVo functionVo);

    public List<Function> findMaxId(FunctionVo vo);

}