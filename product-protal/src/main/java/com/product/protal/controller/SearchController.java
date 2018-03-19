package com.product.protal.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.common.util.HttpClientUtil;
import com.product.protal.pojo.SearchResult;
import com.product.protal.service.SearchService;

@Controller
@RequestMapping("/")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/index")
	public String list(){
		return "query";
	}
	@RequestMapping(value="/")
	public String index(){
		return "query";
	}
	
	@RequestMapping(value="/query")
	public String query(HttpServletRequest request){
		String queryString=request.getParameter("queryString");
		try {
			queryString=new String(queryString.getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			queryString="手机";
		}
		String page=request.getParameter("page");
		if(StringUtils.isBlank(page)){
			page="1";
		}
		SearchResult result=searchService.search(queryString, Integer.valueOf(page));
		request.setAttribute("items", result.getTbItems());
		return "search";
	}

	
}
