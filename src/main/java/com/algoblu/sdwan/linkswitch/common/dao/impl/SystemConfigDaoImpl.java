package com.algoblu.sdwan.linkswitch.common.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.algoblu.sdwan.linkswitch.basic.dao.impl.GenericDaoImpl;
import com.algoblu.sdwan.linkswitch.common.dao.SystemConfigDao;
import com.algoblu.sdwan.linkswitch.basic.po.SystemConfig;

/**
 * 
 * @description 
 *
 * @author  wang yang
 */
@Repository
public class SystemConfigDaoImpl extends GenericDaoImpl<SystemConfig> implements SystemConfigDao {
    private static String FINDCONFIG = "findConfig";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public SystemConfig findConfig(){
        return sqlSessionTemplate.selectOne(FINDCONFIG);
    }
}