package com.product.protal.service;

import com.product.protal.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString,int page);
}
