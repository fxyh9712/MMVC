package com.fxyh.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.fxyh.core.entity.BeanEntity;
import com.fxyh.core.entity.Property;
import com.fxyh.core.util.Contants;
import com.fxyh.core.util.XmlUtil;



/**
 * 
* @ClassName: ApplicationContext  
* @Description: bean容器类  
* @author prosay-fxyh
* @date 2017年12月19日  
*
 */
public class ApplicationContext {

	//存放单例的bean对象
	private Map<String, Object> beans = new HashMap<String, Object>();
	//存放多例的bean原型
	private Map<String, BeanEntity> prototypes = new HashMap<String,BeanEntity>();
	public ApplicationContext(String xml) {
		XmlUtil.log("********************Bean容器框架开始初始化********************");
		Document doc = XmlUtil.getDocFromStream(ApplicationContext.class.getClassLoader().getResourceAsStream(xml));
		Element root = XmlUtil.getRootElement(doc);
		List<Element> beas = XmlUtil.getChildElements(root);
		try {
			for(Element be : beas){//迭代所有bean
				String id = be.attributeValue("id");
				String scope = be.attributeValue("scope");
				String className = be.attributeValue("class");
				XmlUtil.log(id + "解析**************");
				BeanEntity b = null;	//如果是多例，要存储原型
				Class<?> clazz = Class.forName(className);
				Object instance = null;//单例的实例对象
				//默认单例
				if (scope!=null&&Contants.PROTO_STR.equals(scope)) {//原型
					b = new BeanEntity();
					b.setClassName(className);
					b.setScope(scope);
					b.setId(id);
					prototypes.put(id, b);
				}else{
					//这个bean容器管理的都是空构造
					instance = clazz.newInstance();
					beans.put(id, instance);
				}
				
				//迭代Bean的所有属性列表
				for (Object obj : be.elements()) {
					Element prop = (Element)obj;
					String name = prop.attributeValue("name");
					String value = prop.attributeValue("value");
					String ref = prop.attributeValue("ref");
					if (b!=null) {	//代表多例的prototype
						Property pro = new Property();
						pro.setName(name);
						pro.setValue(value);
						pro.setRef(ref);
						b.addProperty(pro);
					} else {		//代表单例，初识化的时候应该创建好实例
						Field field = clazz.getDeclaredField(name); 
						field.setAccessible(true);
						if(value == null){
							field.set(instance,getBean(ref));
						}else{
							field.set(instance, value);
						}
					}
				}
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		XmlUtil.log("********************Bean容器框架初始化完成********************");
	}
	
	
	/**
	 * 
	* @Title: getBean  
	* @Description: 通过id获取bean  
	* @param @param id
	* @param @return    参数  
	* @return Object    返回类型  
	* @throws
	 */
	public Object getBean(String id){
		//先通过单例拿
		Object result = beans.get(id);
		if(result == null){
			//通过原型去new
			BeanEntity b = prototypes.get(id);
			try {
				Class<?> clazz = Class.forName(b.getClassName());
				result = clazz.newInstance();//单例的实例对象
				for(Property prop : b.getProps()){
					Field field = clazz.getDeclaredField(prop.getName()); 
					field.setAccessible(true);
					if(prop.getValue() == null){
						field.set(result,getBean(prop.getRef()));
					}else{
						field.set(result, prop.getValue());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	

}
