package com.fxyh.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxyh.core.annotation.ResponseBody;
import com.fxyh.core.util.Contants;
import com.fxyh.util.SystemUtil;

/**
 * 
* @ClassName: DispatcherServlet  
* @Description: MVC框架的核心控制器  
* @author prosay-fxyh
* @date 2017年12月21日  
*
 */
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {

	//框架容器对象实例
	private BeanContext context;
	@Override
	public void init() throws ServletException {
		//从应用上下文拿到Bean容器
		context = (BeanContext)this.getServletContext().getAttribute(Contants.CONTEXT_NAME);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SystemUtil.initBasePath(request);
		//拿到请求路径
//		System.out.println("request.getContextPath():" + request.getContextPath());
//		System.out.println("request.getRequestURI():" + request.getRequestURI());
//		System.out.println("request.getRequestURL():" + request.getRequestURL());
		///MyMVC
		String contextPath = request.getContextPath();
		///MyMVC/sjkldag/index.do
		String uri = request.getRequestURI();
		String mappingPath = uri.substring(uri.indexOf(contextPath) + contextPath.length() , uri.indexOf(".do"));
		/**
		 * 1.通过映射路径去获取Method 然后要反射调用
		 * 2.反射调用离不开对象实例，所有要获取控制器的实例
		 * 3.然后反射调用
		 */
		//通过映射路径获取对应的方法
		Method method = context.getExecMethod(mappingPath);
		BaseController controller = context.getController(method.getDeclaringClass().getName());
		try {
			//初始化传递请求对象和响应对象
			controller.init(request, response);
			if(method.isAnnotationPresent(ResponseBody.class)){
				method.invoke(controller);
			}else {
				//执行方法
				CustomView view = (CustomView)method.invoke(controller);
				ReturnType type = view.getReturnType();
				ReturnContent content = view.getContent();
				switch (type) {
				case FORWORD:
					request.getRequestDispatcher(Contants.VIEW_PREFIX + content.getUrl() + Contants.VIEW_SUFIX).forward(request, response);
					break;
				case JSON:
					PrintWriter writer = response.getWriter();
					writer.println(content.getJson());
					writer.close();
					break;
				case REDIRECT:
					//从定向到内部资源
					response.sendRedirect(contextPath + "/" + content.getUrl());
					break;
				case FORWORDCHAIN:
					request.getRequestDispatcher(contextPath + "/" + content.getUrl()).forward(request, response);
					break;

				default:
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	
	
	
	
}
