package com.product.service;

import java.util.List;

import com.product.model.ptPermission;

public interface ptPermissionService {
	public List<ptPermission> findPermissionListByUserId(int userid) ;
	public List<ptPermission> findMenuListByUserId(int userid) ;
}
