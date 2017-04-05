package com.algoblu.sdwan.linkswitch.basic.dao;

import com.algoblu.sdwan.linkswitch.basic.vo.SqlAdapter;

import java.util.List;
import java.util.Map;

public interface GenericDao <T> {
	
	/**查询*/
	
	//根据条件实体对象查询记录
	public List<T> find(T entity);
	
	//根据自定义select标签id,条件实体对象查询记录
	public List<T> find(String selectIdName,T entity);
	
	//根据自定义selectId,返回非泛型的记录
	public <V> List<V> findByIdName(String selectIdName,Object entity);
	
	//查询条件中含有迭代条
	public <V> List<V> findForeach(String selectIdName, Object parameter);
	
	//根据条件实体对象查询记录
	public T findUnique(T entity);
	
	//按条件查询记录数
	public int findCount(T entity);
	public int findCount(String selectIdName,T entity) ;
	
	//根据条件映射对象查询记录
	public List<T> findByParams(Map<String,Object> map);
	
	//根据条件映射对象查询唯一记录
	public T findUniqueByParams(Map<String,Object> map);
	
	//根据单一条件查询记录
	public T findUniqueBySingleParams(String name,String value);

	/**保存*/
	public int save(T entity);
	
	/**配置*/
	public int update(T entity);
	
	/**删除*/
	
	//根据条件实体对象删除记录
	public int delete(T entity);
	
	//根据条件映射对象删除记录
    public int deleteByParams(Map<String,Object> map);
    
    //自定义sql
    public int runSql(SqlAdapter sql);
}

