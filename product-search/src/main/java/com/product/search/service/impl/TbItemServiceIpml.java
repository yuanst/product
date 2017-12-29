package com.product.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodct.search.dao.TbItemMapper;
import com.prodct.search.pojo.TbItem;
import com.product.search.service.TbItemService;

@Service
public class TbItemServiceIpml implements TbItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;

	
	
	@Override
	public String importAll() {
		try {
			List<TbItem> items=itemMapper.getItemList();
			
			for (TbItem item : items) {
				SolrInputDocument document=new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				solrServer.add(document);
			}
			solrServer.commit();
		} catch (Exception e) {
			return "shibaile";
		}
		return "ok";
		
	}

}
