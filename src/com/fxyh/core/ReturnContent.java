package com.fxyh.core;

import com.alibaba.fastjson.JSON;

/**
 * 
* @ClassName: ReturnContent  
* @Description: 返回的内容实体  
* @author prosay-fxyh
* @date 2017年12月22日  
*
 */
public class ReturnContent {
	
	/**
	 * 请求转发或重定向的URL
	 */
	private String url;
	/**
	 * 需要返回的json对象
	 */
	private Object obj;
	
	public ReturnContent(String url){
		this.url = url;
	}
	public ReturnContent(Object obj){
		this.obj = obj;
	}
	
	public String getUrl() {
		return url;
	}
	
	public Object getObj() {
		return obj;
	}
	public String getJson(){
		return JSON.toJSONString(obj);
	}
	
	
}
