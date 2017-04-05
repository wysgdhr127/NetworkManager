package com.algoblu.sdwan.linkswitch.common.dao;

import com.algoblu.sdwan.linkswitch.basic.po.SystemConfig;
import com.algoblu.sdwan.linkswitch.basic.dao.GenericDao;

/**
 * 
 * @description
 *
 * @author  wang yang
 */
public interface SystemConfigDao extends GenericDao<SystemConfig> {
    public abstract SystemConfig findConfig();
}