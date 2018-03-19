package com.product.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.common.util.ExceptionUtil;
import com.product.common.util.JsonUtils;
import com.product.common.util.ProductResult;
import com.product.common.util.Util;
import com.product.search.pojo.SearchResult;
import com.product.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	/**
	 * 查询测试
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public String query(HttpServletRequest request){
		String queryString=request.getParameter("q");
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if(StringUtils.isBlank(page)){
			page="1";
		}
		if(StringUtils.isBlank(rows)){
			rows="20";
		}
		SearchResult result=null;
		if(StringUtils.isBlank(queryString)){ //查询条件不能为空
			return null;
		}
		try {
			queryString=new String(queryString.getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			queryString="手机";
		}
		try {
			result=searchService.search(queryString, Integer.valueOf(page), Integer.valueOf(rows));
			return JsonUtils.toJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
