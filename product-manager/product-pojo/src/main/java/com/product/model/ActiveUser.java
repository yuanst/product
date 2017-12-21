package com.product.model;

import java.util.List;

public class ActiveUser {

	private int userId;
	private String loginName;
	private String userName;
	private List<ptPermission> menus;
	private List<ptPermission> permissions;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<ptPermission> getMenus() {
		return menus;
	}
	public void setMenus(List<ptPermission> menus) {
		this.menus = menus;
	}
	public List<ptPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<ptPermission> permissions) {
		this.permissions = permissions;
	}
	
}
