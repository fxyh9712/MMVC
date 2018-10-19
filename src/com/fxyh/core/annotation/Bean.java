package com.fxyh.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Bean  
* @Description: 资源文件注解
* @author prosay-fxyh
* @date 2017年12月19日  
*
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
	/**
	 * 
	* @Title: value  
	* @Description: Bean的名字
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	String value();
	/**
	 * 
	* @Title: single  
	* @Description: 是否是单例
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	boolean single() default true;
}
