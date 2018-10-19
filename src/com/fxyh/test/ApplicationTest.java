package com.fxyh.test;

import com.fxyh.core.ApplicationContext;
import com.fxyh.entity.User;

/**
 * 测试通过xml配置初始化bean容器
 * @author prosay-fxyh
 *
 */
public class ApplicationTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ApplicationContext("beans.xml");
		User u1 = (User) ctx.getBean("user");
		User u2 = (User) ctx.getBean("user");
		u1.query();
		System.out.println(u1.getUserName() + ":" + u1.getPassword());
		System.out.println(u2.getUserName() + ":" + u2.getPassword());
		System.out.println(u1);
		System.out.println(u2);
		System.out.println(u1 == u2);

	}
	
}
