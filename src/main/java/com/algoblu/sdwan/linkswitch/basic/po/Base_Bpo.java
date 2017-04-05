package com.algoblu.sdwan.linkswitch.basic.po;

import com.algoblu.sdwan.linkswitch.basic.Constants;
/**
 * 分页基类
 * @author 2lbj
 * Nov 13, 2012 11:33:51 AM
 */
public class Base_Bpo {
	
	private String userId = "";
	private String notStatus = "";
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNotStatus() {
		return notStatus;
	}

	public void setNotStatus(String notStatus) {
		this.notStatus = notStatus;
	}


	/**
	 * 分页信息
	 */
	// 每页显示记录数
	private Integer showSize = Integer.parseInt(Constants.SYSTEM_PAGE_SIZE);
	
	// 当前页
	private Integer currentPage = 1; 
	
	// 总页数
	private Integer totalPage = 0; 
	
	// 总记录数
	private Integer totalResult = 0; 

	//for example:0,8
	private String limitByClause;
	//for example:id asc
	private String orderByClause;
	
	// 是否停止显示分页
	private Boolean pageDisable = false;

	// sql分页开始行数下标 从0开始
	private Integer tableStartIndex = 0;
	
	//排序列 默认id
	private String orderByColum = "id";
	
	//排序方式,默认 asc
	private String orderBystyle = "asc";
	
	

	public Integer getTableStartIndex() {
		if (!pageDisable) {
			int lspage = currentPage;
			
			if (lspage-1 < 0) {
				lspage = 0;
			}else {
				lspage = lspage - 1;
			}
			
			tableStartIndex=lspage * showSize;
		}else{
			tableStartIndex = null;
		}
		return tableStartIndex;
	}

	public Integer getTotalPage() {
		if (totalResult % showSize == 0){
			
			totalPage = totalResult / showSize;
		}else{
			totalPage = totalResult / showSize + 1;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}

	public Integer getCurrentPage() {
		if (currentPage <= 0){
			currentPage = 1;
		}
		if (currentPage > getTotalPage()){
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Boolean getPageDisable() {
		return pageDisable;
	}

	public void setPageDisable(Boolean pageDisable) {
		this.pageDisable = pageDisable;
	}

	public Integer getShowSize() {
		if (!pageDisable) {
			return showSize;
		}else{
			showSize = null;
		}
		return showSize;
	}

	public void setShowSize(Integer showSize) {
		this.showSize = showSize;
	}

	public String getOrderByColum() {
		return orderByColum;
	}

	public void setOrderByColum(String orderByColum) {
		this.orderByColum = orderByColum;
	}

	public String getOrderBystyle() {
		return orderBystyle;
	}

	public void setOrderBystyle(String orderBystyle) {
		this.orderBystyle = orderBystyle;
	}

	public String getLimitByClause() {
		if(!pageDisable){
			this.getTableStartIndex();
			limitByClause = tableStartIndex.intValue()+","+ showSize.intValue();
		}else{
			limitByClause = null;
		}
		
		return limitByClause;
	}

	public void setLimitByClause(String limitByClause) {
		this.limitByClause = limitByClause;
	}

	public String getOrderByClause() {
		if(orderByClause==null){
			orderByClause = orderByColum+" "+orderBystyle;
		}
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
	
}

