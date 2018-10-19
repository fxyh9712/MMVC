package com.fxyh.core;

/**
 * 
* @ClassName: ReturnType  
* @Description: MVC框架类型  
* @author prosay-fxyh
* @date 2017年12月22日  
*
 */
public enum ReturnType {
	//请求转发
	FORWORD,
	//重定向
	REDIRECT,
	//JSON
	JSON,
	//内部请求转发到控制器
	FORWORDCHAIN;
	
	
}
