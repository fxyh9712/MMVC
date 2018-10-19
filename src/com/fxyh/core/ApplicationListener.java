package com.fxyh.core;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fxyh.core.util.ClassScanner;
import com.fxyh.core.util.Contants;
import com.fxyh.db.DbUtil;

/**
 * 
* @ClassName: ApplicationListener  
* @Description: 监听应用的上下文  
* @author prosay-fxyh
* @date 2017年12月20日  
*
 */
public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//应用程序被加载时
		String basePackage = (String)servletContextEvent.getServletContext().getInitParameter(Contants.PACKAGE);
		//通过基础包路径 扫描下面的所有类
		Map<String, Class<?>> result = ClassScanner.scannerClass(basePackage);
		//初始化Bean容器
		BeanContext b = new BeanContext(result);
		//将Bean容器存入上下文中
		servletContextEvent.getServletContext().setAttribute(Contants.CONTEXT_NAME, b);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		//应用程序被卸载时 释放数据库连接池
		DbUtil.release();

	}

}
