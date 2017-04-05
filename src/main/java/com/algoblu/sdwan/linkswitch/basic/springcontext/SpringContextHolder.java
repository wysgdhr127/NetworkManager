package com.algoblu.sdwan.linkswitch.basic.springcontext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {
	
   private static Logger logger = Logger.getLogger(SpringContextHolder.class);
	
   private static ApplicationContext context;     
   
   public void setApplicationContext(ApplicationContext context) {     
       SpringContextHolder.context =context;     
   }
   
   public static ApplicationContext getApplicationContext() {     
       return context;     
   }     
 
   @SuppressWarnings("unchecked")
   public static <T> T getBean(String name) {  
	   try{
		   return (T) context.getBean(name);     
	   }catch(Exception e){
		   logger.error(e.getMessage(), e);
	   }
	   return null;
   }     
}
