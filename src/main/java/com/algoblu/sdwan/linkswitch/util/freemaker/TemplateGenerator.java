package com.algoblu.sdwan.linkswitch.util.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/** 
* @package com.algoblu.template.util.freemaker
* @author  作者:王阳   E-mail: 282404989@qq.com 
* @version 创建时间：2016年10月11日 下午3:41:07 
* 类说明         根据实体类自动生成 vo dao dao.impl service service.impl action以及struts 中的配置文件
*/ 

public class TemplateGenerator {

	private Logger logger = Logger.getLogger(TemplateGenerator.class);

	
	private String entityPath; 				// 实体类路径
	private String voPath;   				// vo路径
	private String daoPath; 				// dao层路径
	private String daoImplPath;				// daoImpl层路径
	private String servicePath; 			// service层路径
	private String serviceImplPath; 		// serviceImple层路径
	private String actionPath;     			// action层路径
	private String strutsPath;				// struts层路径


	private String daoTemplatePath; 		// 生成dao 模版路径
	private String daoImplTemplatePath; 	// 生成dao.impl 模版路径
	private String serviceTemplatePath; 	// 生成service 模版路径
	private String serviceImplTemplatePath; // 生成serviceImple 模板路径
	private String voTemplatePath; 			// 生成vo 模板路径
	private String actionTemplatePath;  	// 生成action 模板路径
	private String strutsTemplatePath;  	// 生成struts 模板路径
	
	private String baseDaoImplPath; 		// dao.impl基类路径
	private String baseDaoPath;     		// dao基类路径
	private String baseServiceImplPath; 	// Service实现路径
	private String baseServicePath;     	// Service接口路径
	

	/**
	 * 初始化参数 从配置文件中得到
	 */
	public void init() {
		String propertyPath = "src/main/resources/config.properties"; // 使用默认路径
		init(propertyPath);
	}
	
	public void init(String propertyPath) {
		Map<String, String> properties = Property.getProperties(propertyPath);
		entityPath = properties.get("entityPath");
		voPath = properties.get("voPath");
		daoPath = properties.get("daoPath");
		daoImplPath = properties.get("daoImplPath");
		servicePath = properties.get("servicePath");
		serviceImplPath = properties.get("serviceImplPath");
		actionPath = properties.get("actionPath");
		strutsPath = properties.get("strutsPath");
		
		daoTemplatePath = properties.get("daoTemplatePath");
		daoImplTemplatePath = properties.get("daoImplTemplatePath");
		serviceTemplatePath = properties.get("serviceTemplatePath");
		voTemplatePath = properties.get("voTemplatePath");
		serviceImplTemplatePath = properties.get("serviceImplTemplatePath");
		actionTemplatePath = properties.get("actionTemplatePath");
		strutsTemplatePath = properties.get("strutsTemplatePath");
		
		baseDaoPath = properties.get("baseDaoPath");
		baseDaoImplPath = properties.get("baseDaoImplPath");
		baseServicePath = properties.get("baseServicePath");
		baseServiceImplPath = properties.get("baseServiceImplPath");
	}

	/**
	 * @param path
	 * @return 根据包的路径得到package XXX中的XXX
	 */
	private String getImportPath(String path) {
		StringBuffer result = new StringBuffer();
		String[] strings = path.split("/");
		for (int i = 3, size = strings.length; i < size; i++) {
			result.append(".").append(strings[i]);
		}
		return result.substring(1);
	}

	/**
	 * @param entity
	 * @return 得到实体名称
	 */
	private String getEntityName(File entity) {
		return entity.getName().substring(0, entity.getName().indexOf("."));
	}

	/**
	 * @param s
	 *            String
	 * @return 首字母小写
	 */
	public static String firstCharLowerCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public void buildTemplate(boolean daoFlag, boolean daoImplFlag, boolean serviceFlag,boolean serviceImplFlag, boolean voFlag) throws IOException {
		
		File entityDir = new File(entityPath);
		entityPath = getImportPath(entityPath);
		//得到实体包下所有的实体java文件
		File[] entities = entityDir.listFiles();
		String daoImport = getImportPath(daoPath);
		String daoImplImport = getImportPath(daoImplPath);
		String serviceImport = getImportPath(servicePath);
		String serviceImplImport = getImportPath(serviceImplPath);
		String voImport = getImportPath(voPath);
		String actioinImport = getImportPath(actionPath);
		// baseDao, baseImplDao 名字和路径
		DefaultTemplate defaultTemplate = new DefaultTemplate();
		String baseDaoImport = getImportPath(baseDaoPath);
		String baseDaoImplImport = getImportPath(baseDaoImplPath);
		String baseServiceImport = getImportPath(baseServicePath);
		String baseServiceImplImport = getImportPath(baseServiceImplPath);
		//开始create了
		for (File entity : entities) {
			// 过滤掉配置文件
			System.out.println(entity.getName());
			if (!entity.getName().endsWith("java")||entity.getName().equals("Base_Bpo.java")) {
				continue;
			}

			
			
			String entityName = getEntityName(entity);
			String entityViable= firstCharLowerCase(entityName);
			
			String serviceImportPkg = serviceImport + ".model_" + entityViable + ".service";
			String serviceImplImportPkg = serviceImplImport+".model_" + entityViable + ".service.impl";
			String voImportPkg = voImport + ".model_" + entityViable + ".vo";
			String actionImportPkg = actioinImport + ".model_" + entityViable + ".action";
			
			
			String serviceSavePath = servicePath +"/model_"+ entityViable + "/service";
			String serviceImplSavePath = serviceImplPath +"/model_"+ entityViable + "/service/impl";			
			String voSavePath = voPath +"/model_"+ entityViable + "/vo";
			String actionSavePath = actionPath +"/model_"+ entityViable + "/action";
			
			
			String daoName = entityName + "Dao";
			String daoEntity = firstCharLowerCase(daoName);			
			String daoImplName = entityName + "DaoImpl";
			String serviceName = entityName + "Service";
			String serviceEntity = firstCharLowerCase(serviceName);
			String serviceImplName = entityName + "ServiceImpl";
			String actionName = entityName + "Action";
			
			String modelPath = entityPath + "." + entityName;
			String dao = daoImport + "." + daoName;
			String service = serviceImportPkg + "." + serviceName;
			String action = actionImportPkg + "." + actionName;
			String baseDao = baseDaoImport+ "." + defaultTemplate.getBaseDaoName();
			String baseDaoImpl = baseDaoImplImport+ "." + defaultTemplate.getBaseDaoImplName();
			String baseService = baseServiceImport+ "." + defaultTemplate.getBaseServiceName();
			String baseServiceImpl = baseServiceImplImport+ "." + defaultTemplate.getBaseServiceImplName();
			
			String voName =  entityName + "Vo";
			
			//~ 生成dao ======================================================================================
			if (daoFlag) {
				Template template = new Configuration().getTemplate(daoTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setBaseDaoPath(baseDao);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setPackagePath(daoImport);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, daoPath,
						entityName + "Dao.java", template);
			}
			//~ 生成daoImpl ======================================================================================
			if (daoImplFlag) {
				Template template = new Configuration().getTemplate(daoImplTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(daoImplImport);
				defaultTemplate.setBaseDaoImplPath(baseDaoImpl);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setDaoImplName(daoImplName);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				defaultTemplate.setDaoName(daoName);
				createFile(defaultTemplate, daoImplPath, entityName
						+ "DaoImpl.java", template);
			}
			//~ 生成service ======================================================================================
			if (serviceFlag) {
				Template template = new Configuration().getTemplate(serviceTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(serviceImportPkg);
				defaultTemplate.setVoName(voName);
				defaultTemplate.setVoPath(voImportPkg+"."+voName);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setBaseServicePath(baseService);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, serviceSavePath, entityName
						+ "Service.java", template);
			}
			//~ 生成serviceImpl ======================================================================================
			if(serviceImplFlag){
				Template template = new Configuration().getTemplate(serviceImplTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(serviceImplImportPkg);
				defaultTemplate.setVoName(voName);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setVoPath(voImportPkg+"."+voName);
				defaultTemplate.setServicePath(service);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setBaseServiceImplPath(baseServiceImpl);
				defaultTemplate.setServiceImplName(serviceImplName);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, serviceImplSavePath, entityName
						+ "ServiceImpl.java", template);
			}
			
			//~ 生成vo ======================================================================================
			if (voFlag) {
				Template template = new Configuration().getTemplate(voTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(voImportPkg);
				defaultTemplate.setVoName(voName);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setServicePath(service);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setBaseServiceImplPath(baseServiceImpl);
				defaultTemplate.setServiceImplName(serviceImplName);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, voSavePath, entityName
						+ "Vo.java", template);
			}
			
			//~ 生成Action ======================================================================================
			if (voFlag) {
				Template template = new Configuration().getTemplate(actionTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(actionImportPkg);
				defaultTemplate.setActionName(actionName);
				defaultTemplate.setActionPath(action);
				defaultTemplate.setVoName(voName);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setVoPath(voImportPkg+"."+voName);
				defaultTemplate.setServicePath(service);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setBaseServiceImplPath(baseServiceImpl);
				defaultTemplate.setServiceImplName(serviceImplName);
				defaultTemplate.setServiceEntity(serviceEntity);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, actionSavePath, entityName
						+ "Action.java", template);
			}
			
			//~ 生成Struts ======================================================================================
			if (voFlag) {
				Template template = new Configuration().getTemplate(strutsTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(actionImportPkg);
				defaultTemplate.setActionName(actionName);
				defaultTemplate.setActionPath(action);
				defaultTemplate.setVoName(voName);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setVoPath(voImportPkg+"."+voName);
				defaultTemplate.setServicePath(service);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setBaseServiceImplPath(baseServiceImpl);
				defaultTemplate.setServiceImplName(serviceImplName);
				defaultTemplate.setServiceEntity(serviceEntity);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				createFile(defaultTemplate, strutsPath, "struts-"+entityViable + ".xml", template);
			}
		}
	}

	/**
	 * 生成文件
	 * @param object
	 * @param savePath
	 * @param fileName
	 * @param template
	 */
	public void createFile(Object object, String savePath, String fileName,
			Template template) {
		String realFileName = savePath + "/" + fileName;

		String realSavePath = savePath;

		File newsDir = new File(realSavePath);
		if (!newsDir.exists()) {
			logger.info("create dir :" + realSavePath);
			newsDir.mkdirs();
		}
		//如果已經存在不创建
		File realFile = new File(realFileName);
		if (realFile.exists()) {
			logger.info(fileName + " exists , don't create !");
			return;
		}
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("defaultTemplate", object);
		try {
			// SYSTEM_ENCODING = "UTF-8";
			Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
			template.process(rootMap, out);
			logger.info(fileName + " create success !");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			TemplateGenerator mg = new TemplateGenerator();
			mg.init();
			mg.buildTemplate(true,true, true,true,true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
