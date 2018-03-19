package com.product.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.common.util.HttpClientUtil;
import com.product.search.service.TbItemService;

@Controller
public class TbItemController {

	@Autowired
	private TbItemService itemService;
	
	@RequestMapping(value="/importAll")
	@ResponseBody
	public String getAllimportTbItem(){
		String tbitem=itemService.importAll();
		return tbitem;
	}
	@RequestMapping("index")
	public String ceshi(){
		return "ss";
	}
	@RequestMapping("/")
	@ResponseBody
	public String mei(){
		return "ok";
	}
	@RequestMapping(value="cs")
	@ResponseBody
	public Object cs(HttpServletRequest request){
		String data=null;
		String callback=request.getParameter("callback");
		if(null!=callback){
			data="测试成功";
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return data;
	}
	
}
