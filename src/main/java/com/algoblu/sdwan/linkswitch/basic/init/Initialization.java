package com.algoblu.sdwan.linkswitch.basic.init;


import com.algoblu.sdwan.linkswitch.basic.po.SystemConfig;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.common.dao.SystemConfigDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class Initialization extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		loadSystemConfig();
	}
	
	/**
	 * 载入系统配置参数
	 * 
	 * TODO
	 */
	private void loadSystemConfig(){
		
		SystemConfigDao systemConfigDao = SpringContextHolder.getBean("systemConfigDao");
		ConcurrentHashMap<String,String> systemConfigMap = SpringContextHolder.getBean("systemConfigMap");
		
		SystemConfig condition = new SystemConfig();
		condition.setPageDisable(true);
		List<SystemConfig> list = systemConfigDao.find(condition);
		for(SystemConfig sc : list){
			systemConfigMap.put(sc.getConfigName(), sc.getConfigValue());
		}
	}
	
}
