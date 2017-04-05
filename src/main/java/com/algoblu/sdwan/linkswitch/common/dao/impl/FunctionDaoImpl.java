package com.algoblu.sdwan.linkswitch.common.dao.impl;

import com.algoblu.sdwan.linkswitch.basic.dao.impl.GenericDaoImpl;
import com.algoblu.sdwan.linkswitch.basic.po.Function;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;
import com.algoblu.sdwan.linkswitch.common.dao.FunctionDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @description 
 *
 * @author  wang yang
 */
@Repository
public class FunctionDaoImpl extends GenericDaoImpl<Function> implements FunctionDao {
    @Override
    public List<Function> selectFunction(FunctionVo functionVo) {
        return this.getSqlSession().selectList(clazz.getName() + ".selectFunction", functionVo);
    }

    @Override
    public List<Function> findMaxId(FunctionVo vo) {
        // TODO Auto-generated method stub
        return this.getSqlSession().selectList(clazz.getName() + ".selectMaxId", vo);
    }
}