package com.product.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.product.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
