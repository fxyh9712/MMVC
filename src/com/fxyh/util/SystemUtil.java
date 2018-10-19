package com.fxyh.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SystemUtil {

	public static final String USERENCODE = "[prosay]";
	public static final String USERADMIN = "admin";
	public static final String USERNORMAL = "user";
	public static final String USERALL = "all";
	public static final String USERNON = "non";
	public static final String ADMINLOGIN="/admin/login.do";
	public static final String USERLOGIN="/user/login.do";
	
	public static void initBasePath(HttpServletRequest request) {
		/*
		 * System.out.println(request.getServerName()); //localhost
		 * System.out.println(request.getServerPort()); //8080
		 * System.out.println(request.getContextPath()); //"/shop"
		 * System.out.println(request.getScheme()); //http
		 */
		ServletContext application = request.getServletContext();
		if (application.getAttribute("basePath") == null) {
			application.setAttribute("basePath", request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath());
		}
		if (application.getAttribute("projectName") == null) {
			application.setAttribute("projectName", request.getContextPath());
		}
	}
	
	public static void setAdmin(HttpServletRequest request , Map<String, Object> admin) {
		request.getSession().setAttribute("admin", admin);
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> setAdmin(HttpServletRequest request) {
		return (Map<String, Object>) request.getSession().getAttribute("admin");
	}

	public static String stringIsEmpty(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}
	
}
