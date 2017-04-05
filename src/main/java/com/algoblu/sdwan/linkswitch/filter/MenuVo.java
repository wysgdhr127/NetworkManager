package com.algoblu.sdwan.linkswitch.filter;

import java.util.List;


@SuppressWarnings("serial")
public class MenuVo implements java.io.Serializable{
	
	//操作id
	private Long id;
	private String name;
	private String url;
	private String type;
	private List<MenuVo> nextLevelMenu;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuVo> getNextLevelMenu() {
		return nextLevelMenu;
	}
	public void setNextLevelMenu(List<MenuVo> nextLevelMenu) {
		this.nextLevelMenu = nextLevelMenu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "MenuVo [id=" + id + ", name=" + name + ", url=" + url + ", nextLevelMenu="
				+ nextLevelMenu + "]";
	}
	
	
}



