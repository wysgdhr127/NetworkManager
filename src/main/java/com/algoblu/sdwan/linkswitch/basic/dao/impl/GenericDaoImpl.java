package com.algoblu.sdwan.linkswitch.basic.dao.impl;

import com.algoblu.sdwan.linkswitch.basic.dao.GenericDao;
import com.algoblu.sdwan.linkswitch.basic.vo.SqlAdapter;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GenericDaoImpl<T> extends SqlSessionDaoSupport implements GenericDao<T>{
	
	private static Logger logger = Logger.getLogger(GenericDaoImpl.class);
	
	protected Class<T> clazz;
	
	/**
	 * 将条件存放在对象中的查询方式
	 */
	public static final String POSTFIX_SELECT = ".select";

	
	/**
	 * 将条件存放在对象中的查询方式合计
	 */
	public static final String POSTFIX_COUNT = ".count";
	
	/**
	 * 将条件存放在映射中的查询方式
	 */
	public static final String POSTFIX_SELECT_BY_MAP = ".selectByMap";
	
	/**
	 * 将条件存放在对象中的保存方式
	 */
	public static final String POSTFIX_INSERT = ".insert";

	/**
	 * 将条件存放在对象中的配置方式
	 */
	public static final String POSTFIX_UPDATE = ".update";

	/**
	 * 将条件存放在对象中的删除方式
	 */
	public static final String POSTFIX_DELETE = ".delete";
	
	/**
	 * 将条件存放在映射中的删除方式
	 */
	public static final String POSTFIX_DELETE_MAP = ".deleteByMap";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl(){
		// 获取有泛型信息的父类Class
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获取这个类型参数的真实类�?
		this.clazz = (Class) pt.getActualTypeArguments()[0];
//		System.out.println("---> clazz = " + clazz);
	}
	//查询
	public List<T> find(T entity) {
		try {
			return getSqlSession().selectList(clazz.getName()+POSTFIX_SELECT, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.find exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	public List<T> find(String selectIdName, T entity) {
		try {
			return getSqlSession().selectList(clazz.getName()+ "." +selectIdName, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.find exception:"+e.getMessage(),e);
			return null;
		}
	}
	public int findCount(String selectIdName,T entity) {
		try {			
			int count = (Integer)getSqlSession().selectOne(clazz.getName()+"."+selectIdName, entity);
			return count;
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findCount exception:"+e.getMessage(),e);
			return -1;
		}
	}
	
	public <V> List<V> findByIdName(String selectIdName, Object entity) {
		try {
			return getSqlSession().selectList(clazz.getName()+ "." +selectIdName, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.find exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	public <V> List<V> findForeach(String selectIdName, Object parameter){
		try {
			return getSqlSession().selectList(clazz.getName()+ "." +selectIdName, parameter);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findForeach exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T findUnique(T entity) {
		try {
			return (T)getSqlSession().selectOne(clazz.getName()+POSTFIX_SELECT, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findUnique exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	public int findCount(T entity) {
		try {			
			int count = (Integer)getSqlSession().selectOne(clazz.getName()+POSTFIX_COUNT, entity);
			return count;
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findCount exception:"+e.getMessage(),e);
			return -1;
		}
	}

	public List<T> findByParams(Map<String,Object> map){
		try {
			return this.getSqlSession().selectList(clazz.getName()+POSTFIX_SELECT_BY_MAP, map);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findByParams exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")

	public T findUniqueByParams(Map<String,Object> map){
		try {
			return (T)getSqlSession().selectOne(clazz.getName()+POSTFIX_SELECT_BY_MAP, map);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findUniqueByParams exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findUniqueBySingleParams(String name,String value){
		try {
			Map<String,Object> map =new HashMap<String,Object>();
			map.put(name, value);
			return (T)getSqlSession().selectOne(clazz.getName()+POSTFIX_SELECT_BY_MAP, map);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.findUniqueBySingleParams exception:"+e.getMessage(),e);
			return null;
		}
	}
	
	//保存

	public int save(T entity){
		try {
			return this.getSqlSession().insert(
					clazz.getName() + POSTFIX_INSERT, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.save exception:"+e.getMessage(),e);
			return -1;
		}
	}
	
	//配置

	public int update(T entity){
		try{
			return this.getSqlSession().update(clazz.getName()+POSTFIX_UPDATE, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.update exception:"+e.getMessage(),e);
			return -1;
		}
	}
	
	//删除

	public int delete(T entity) {
		try{
			return this.getSqlSession().delete(clazz.getName()+ POSTFIX_DELETE, entity);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.delete exception:"+e.getMessage(),e);
			return -1;
		}
	}
	

	public int deleteByParams(Map<String,Object> map){
		try{	
			return this.getSqlSession().delete(clazz.getName() + POSTFIX_DELETE_MAP, map);
		} catch (Exception e) {
			logger.error("GenericDaoImpl.deleteByParams exception:"+e.getMessage(),e);
			return -1;
		}
		
	}

	public int runSql(SqlAdapter sql) {
		try{	
			this.getSqlSession().selectOne(clazz.getName()+".runSql",sql);
			return 1;
		} catch (Exception e) {
			logger.error("GenericDaoImpl.runSql exception:"+e.getMessage(),e);
			return -1;
		}
	}

}
