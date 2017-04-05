package com.algoblu.sdwan.linkswitch.basic.service;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;

import java.util.List;


public interface ModelRoleService<T> {
	
	/**
	 * 搜索列表记录
	 * 
	 * @return returnVoList
	 */
	public List<T> searchList(T po,boolean isPage);

	/**
	 * 搜索单一记录
	 * 
	 * @return returnVo
	 */
	public T searchById(Long id);
	
	/**
	 * 增加
	 * 
	 * @return
	 */
	public ErrorVo add(T po);
	
	/**
	 *修改 
	 * 
	 * @return
	 */
	public ErrorVo update(T po);
	
	
	/**
	 *删除	
	 * 
	 * @return
	 * @throws Exception 
	 */
	public ErrorVo delete(Long id); 
	
}
