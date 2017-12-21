package com.product.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	
	//初始化分页相关信息  
    protected void initPage(Map<String,Object> map, Integer pageNum, Integer pageSize, Integer totalCount){  
        if(null==pageSize || pageSize.equals("")){  
            pageSize = 20;  
        }  
        if(pageSize>50){  
            pageSize = 50;  
        }  
        Integer totalPage = (totalCount+pageSize-1)/pageSize;  
        if(null==pageNum){  
            pageNum = 1;  
        }else if(pageNum>totalPage){  
            pageNum = totalPage;  
        }  
        map.put("startIndex", Pager.getStartIndex(pageNum, pageSize));  
        map.put("pageNum", pageNum);  
        map.put("totalPage", totalPage);  
        map.put("pageSize", pageSize);  
        map.put("totalCount", totalCount);  
          
    }  
      
    //将相关数据放入model  
    protected void initResult(HttpServletRequest request, Map<String,Object> map){  
        Iterator it = map.entrySet().iterator();   
        while(it.hasNext()){   
            Map.Entry m = (Map.Entry)it.next();   
            request.setAttribute(m.getKey().toString(), m.getValue());  
       }   
    }  
}
