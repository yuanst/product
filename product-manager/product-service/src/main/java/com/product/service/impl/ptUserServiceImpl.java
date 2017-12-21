package com.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.mapper.ptUserMapper;
import com.product.model.ptUser;
import com.product.model.ptUserExample;
import com.product.model.ptUserExample.Criteria;
import com.product.service.ptUserService;

/**
 * 
 *<p>Title: ptUserServiceImpl</p>
 * @author yuanst
 * <p>Company:</p>
 * @date 2017年12月21日,上午11:19:16
 * @version 1.0
 */
@Service
public class ptUserServiceImpl implements ptUserService {
	
	@Autowired
	private ptUserMapper userMapper;

	@Override
	public ptUser getByName(String loginName) {
		
		return userMapper.getByName(loginName);
	}

}
