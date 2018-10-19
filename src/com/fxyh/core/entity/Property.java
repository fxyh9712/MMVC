package com.fxyh.core.entity;

import java.lang.reflect.Field;

/**
 * 
* @ClassName: Property  
* @Description: Bean原型中的属性对象  
* @author prosay-fxyh
* @date 2017年12月19日  
*
 */
public class Property {

	private Field field;
	private String name;	//属性名字
	private String value;	//属性值
	private String ref;		//依赖的对象

	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
