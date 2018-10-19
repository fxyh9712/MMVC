package com.fxyh.core;

import java.util.List;

import javax.servlet.http.Part;

/**
 * 
    * @ClassName: Uploadable  
    * @Description: 控制器要能够上传文件  
    * @author prosay-fxyh 
    * @date 2017年12月22日   
    *
 */
public interface Uploadable {

	/**
	 * 
	    * @Title: setFileNames  
	    * @Description: 提供给DispacherServlet来反射调用时传递文件的参数  
	    * @param @param fileNames    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	public void setFileNames(List<String> fileNames);
	
	/**
	 * 
	    * @Title: setFileParts  
	    * @Description: 提供给DispacherServlet来反射调用是传递文件的参数  
	    * @param @param files    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	public void setFileParts(List<Part> files);
	
}
