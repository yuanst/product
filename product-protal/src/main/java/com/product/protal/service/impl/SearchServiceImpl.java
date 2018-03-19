package com.product.protal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.product.common.util.HttpClientUtil;
import com.product.common.util.ProductResult;
import com.product.protal.pojo.SearchResult;
import com.product.protal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	  @Value("#{config['SEARCH_BASE_URL']}")
	    private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			String json=HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			ProductResult result=ProductResult.formatToList(json, SearchResult.class);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
