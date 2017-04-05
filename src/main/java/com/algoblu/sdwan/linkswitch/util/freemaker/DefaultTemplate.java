package com.algoblu.sdwan.linkswitch.util.freemaker;

/** 
* @package com.algoblu.template.util.freemaker
* @author 作者:王阳   E-mail: 282404989@qq.com 
* @version 创建时间：2016年10月11日 下午3:42:47 
* 类说明 
*/


public class DefaultTemplate {
	//~ Field   =============================================================================================
	private String packagePath; //当前包路径
	private String baseDaoPath; //BaseDao接口路径
	private String baseDaoImplPath; //BaseDao实现类路径
	private String baseServicePath; //BaseService接口路径
	private String baseServiceImplPath; //BaseService实现类路径
	private String modelPath;  //当前实体类路径
	private String daoPath;		//当前dao接口路径	
	private String servicePath; //当前service接口路径
	private String voPath;      //当前Vo路径	
	private String actionPath;  //当前action路径
	
	private String entityName;	//当前实体类名称
	private String daoName;		//当前dao接口名称
	private String daoImplName; //dao实现类名称	
	private String serviceName; //service
	private String serviceImplName; //serviceImple名称
	private String daoEntity;   //dao实体
	private String serviceEntity;
	private String entityViable; //实体变量
	private String actionName;
	
	private String baseDaoName = "GenericDao";
	private String baseDaoImplName = "GenericDaoImpl";
	
	private String baseServiceName = "ModelRoleService";
	private String baseServiceImplName = "BasicService";
	
	private String voName;
	private String fields;
	private String queryField;
	private String whereClause;
	private String updateClause;
	private String insertField;
	private String insertClause;
	
	//~ Get and Set Methods =================================================================================
	public String getPackagePath() {
		return packagePath;
	}
	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	public String getBaseDaoPath() {
		return baseDaoPath;
	}
	public void setBaseDaoPath(String baseDaoPath) {
		this.baseDaoPath = baseDaoPath;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getDaoName() {
		return daoName;
	}
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getBaseDaoImplPath() {
		return baseDaoImplPath;
	}
	public void setBaseDaoImplPath(String baseDaoImplPath) {
		this.baseDaoImplPath = baseDaoImplPath;
	}
	public String getDaoPath() {
		return daoPath;
	}
	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}
	public String getDaoImplName() {
		return daoImplName;
	}
	public void setDaoImplName(String daoImplName) {
		this.daoImplName = daoImplName;
	}
	public String getBaseDaoName() {
		return baseDaoName;
	}
	public void setBaseDaoName(String baseDaoName) {
		this.baseDaoName = baseDaoName;
	}
	public String getBaseDaoImplName() {
		return baseDaoImplName;
	}
	public void setBaseDaoImplName(String baseDaoImplName) {
		this.baseDaoImplName = baseDaoImplName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDaoEntity() {
		return daoEntity;
	}
	public void setDaoEntity(String daoEntity) {
		this.daoEntity = daoEntity;
	}
	public String getEntityViable() {
		return entityViable;
	}
	public void setEntityViable(String entityViable) {
		this.entityViable = entityViable;
	}
	public String getServiceImplName() {
		return serviceImplName;
	}
	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}
	public String getServicePath() {
		return servicePath;
	}
	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	public String getBaseServiceName() {
		return baseServiceName;
	}
	public void setBaseServiceName(String baseServiceName) {
		this.baseServiceName = baseServiceName;
	}
	public String getBaseServiceImplName() {
		return baseServiceImplName;
	}
	public void setBaseServiceImplName(String baseServiceImplName) {
		this.baseServiceImplName = baseServiceImplName;
	}
	public String getBaseServicePath() {
		return baseServicePath;
	}
	public void setBaseServicePath(String baseServicePath) {
		this.baseServicePath = baseServicePath;
	}
	public String getBaseServiceImplPath() {
		return baseServiceImplPath;
	}
	public void setBaseServiceImplPath(String baseServiceImplPath) {
		this.baseServiceImplPath = baseServiceImplPath;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public String getVoPath() {
		return voPath;
	}
	public void setVoPath(String voPath) {
		this.voPath = voPath;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getServiceEntity() {
		return serviceEntity;
	}
	public void setServiceEntity(String serviceEntity) {
		this.serviceEntity = serviceEntity;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getQueryField() {
		return queryField;
	}
	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	public String getUpdateClause() {
		return updateClause;
	}
	public void setUpdateClause(String updateClause) {
		this.updateClause = updateClause;
	}
	public String getInsertField() {
		return insertField;
	}
	public void setInsertField(String insertField) {
		this.insertField = insertField;
	}
	public String getInsertClause() {
		return insertClause;
	}
	public void setInsertClause(String insertClause) {
		this.insertClause = insertClause;
	}	
	
}


