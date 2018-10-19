package com.fxyh.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fxyh.core.entity.PageData;

/**
 * 
* @ClassName: BaseController  
* @Description: 控制器的基类  
* @author prosay-fxyh
* @date 2017年12月21日  
*
 */
public class BaseController {
	protected Logger log = Logger.getLogger(BaseController.class);
	/**
	 * ThreadLocal
	 * 它会在每个子线程中开辟一个内存空间
	 * 它存储的对象生命周期只有当前线程
	 */
	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<PageData> pageData = new ThreadLocal<PageData>();
	
	@SuppressWarnings("static-access")
	public void init(HttpServletRequest request,HttpServletResponse response) {
		this.request.set(request);
		this.response.set(response);
		pageData.set(new PageData(request));
	}
	
	@SuppressWarnings("static-access")
	public PageData getPageData() {
		return this.pageData.get();
	}
	
	/**
	 * 
	    * @Title: getSessionAttr  
	    * @Description: 从session中拿数据  
	    * @param @param attrName
	    * @param @return    参数  
	    * @return Object    返回类型  
	    * @throws
	 */
	@SuppressWarnings("static-access")
	public Object getSessionAttr(String attrName) {
		return this.request.get().getSession().getAttribute(attrName);
	}
	
	/**
	 * 
	    * @Title: setSessionAttr  
	    * @Description: 往session中存放属性
	    * @param @param attrName
	    * @param @param obj    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@SuppressWarnings("static-access")
	public void setSessionAttr(String attrName, Object obj) {
		this.request.get().getSession().setAttribute(attrName, obj);
	}
	/**
	 * 
	* @Title: getReq  
	* @Description: 拿到当前线程的请求对象
	* @param @return    参数  
	* @return HttpServletRequest    返回类型  
	* @throws
	 */
	@SuppressWarnings("static-access")
	protected HttpServletRequest getReq(){
		return this.request.get();
	}
	
	/**
	 * 
	* @Title: getRep  
	* @Description: 拿到当前线程的响应对象  
	* @param @return    参数  
	* @return HttpServletResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings("static-access")
	protected HttpServletResponse getRep() {
		return this.response.get();
	}
	
	/**
	 * 
	    * @Title: put  
	    * @Description: 往请求域中存放属性  
	    * @param @param attrName
	    * @param @param attrValue    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	protected void put(String attrName,Object attrValue) {
		request.get().setAttribute(attrName, attrValue);
	}
	
	/**
	 * 
	    * @Title: get  
	    * @Description: 从请求域中拿属性  
	    * @param @param attrName    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	protected Object get(String attrName) {
		return request.get().getAttribute(attrName);
	}
	
	protected void writeToResponse(String str) {
		try {
			PrintWriter writer = getRep().getWriter();
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			log.error("Response输出到前台错误！" , e.getCause());
		}
	}
}
