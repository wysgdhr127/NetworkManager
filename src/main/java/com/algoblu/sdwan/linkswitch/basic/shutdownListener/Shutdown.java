package com.algoblu.sdwan.linkswitch.basic.shutdownListener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Shutdown implements ServletContextListener {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("容器已经销毁");
	}

}
