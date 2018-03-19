package com.product.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.search.dao.SearchDao;
import com.product.search.pojo.SearchResult;
import com.product.search.pojo.TbItem;


@Repository
public class SearchDaoImpl implements SearchDao{
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		SearchResult result=new SearchResult();
		QueryResponse queryResponse=solrServer.query(query);
		SolrDocumentList documentList=queryResponse.getResults();
		result.setRecordCount(documentList.getNumFound());
		List<TbItem> itemList=new ArrayList<TbItem>();
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		for (SolrDocument document : documentList) {
			TbItem item=new TbItem();
			item.setId(String.valueOf(document.get("id")));
			List<String> list=highlighting.get(document.get("id")).get("item_title");
			String title="";
			if(list!=null&&list.size()>0){
				title=list.get(0);
			}else{
				title=String.valueOf(document.get("item_title"));
			}
			item.setTitle(title);
			item.setPrice((long) document.get("item_price"));
			itemList.add(item);
		}
		result.setTbItems(itemList);
		return result;
	}

}
