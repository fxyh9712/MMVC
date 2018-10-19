package com.fxyh.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: Bean  
* @Description: Bean原型 用来存储xml文档中的配置  
* @author prosay-fxyh
* @date 2017年12月19日  
*
 */
public class BeanEntity {
	
	private String id;					//id唯一标识
	private String scope;				//bean的生命周期范围
	private String className;			//bean 类的全路径
	private List<Property> props = new ArrayList<Property>();	//属性列表
	private Class<?> clazz;
	
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public void addProperty(Property prop){
		props.add(prop);
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Property> getProps() {
		return props;
	}
	public void setProps(List<Property> props) {
		this.props = props;
	}
	
}
