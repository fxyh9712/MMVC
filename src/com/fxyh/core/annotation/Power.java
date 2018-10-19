package com.fxyh.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
    * @ClassName: Power  
    * @Description:	标注权限控制
    * @author prosay-fxyh 
    * @date 2017年12月22日  
    *
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Power {
	String value();
}