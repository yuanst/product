package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.mapper.ptPermissionMapper;
import com.product.model.ptPermission;
import com.product.service.ptPermissionService;

@Service
public class ptPermissionServiceImpl implements ptPermissionService {
	
	@Autowired
	private ptPermissionMapper permissionMapper;

	@Override
	public List<ptPermission> findPermissionListByUserId(int userId) {
		return permissionMapper.findPermissionListByUserId(userId);
	}

	@Override
	public List<ptPermission> findMenuListByUserId(int userId) {
		return permissionMapper.findMenuListByUserId(userId);
	}

}
