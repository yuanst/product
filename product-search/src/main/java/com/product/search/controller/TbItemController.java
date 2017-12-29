package com.product.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
