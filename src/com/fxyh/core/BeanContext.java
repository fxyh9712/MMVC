package com.fxyh.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fxyh.core.annotation.Autowired;
import com.fxyh.core.annotation.Bean;
import com.fxyh.core.annotation.Controller;
import com.fxyh.core.annotation.RequestMapping;
import com.fxyh.core.entity.BeanEntity;
import com.fxyh.core.entity.Property;
import com.fxyh.core.util.XmlUtil;

/**
 * 
* @ClassName: BeanContext  
* @Description: Bean容器的初始化操作 只针对注解
* @author prosay-fxyh
* @date 2017年12月20日  
*
 */
public class BeanContext {
	
	/**
	 * 扫描到的所有类 必备
	 */
	private Map<String, Class<?>> classes;
	//存放单例的bean对象	key是id
	private Map<String, Object> beans = new HashMap<String, Object>();
	//存放多例的bean原型	多例
	private Map<String, BeanEntity> prototypes = new HashMap<String, BeanEntity>();
	//存放控制器实例 	key是类名
	private Map<String, Object> controllers= new HashMap<String, Object>();
	//存放路径映射	key是路径 
	private Map<String, Method> reqs = new HashMap<String, Method>();
	
	/**
	 * 防止空构造new出来
	 * @param classes
	 */
	public BeanContext(Map<String, Class<?>> classes){
		this.classes = classes;
		init();
	}
	
	/**
	 * 
	* @Title: init  
	* @Description: 容器初始化
	* @param     参数  
	* @return void    返回类型  
	* @throws
	 */
	//@SuppressWarnings("unchecked")
	private void init(){
		XmlUtil.log("***********************PROSAY容器初识化开始************************");
		try {
			Iterator<String> itro = classes.keySet().iterator();
			while(itro.hasNext()){
				String key = itro.next();
				Class<?> clazz = classes.get(key);
				if(clazz.isAnnotationPresent(Bean.class)){//bean注解
					initBean(clazz);
				}else if(clazz.isAnnotationPresent(Controller.class)){//控制器注解
					initController(clazz);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		XmlUtil.log("***********************PROSAY容器初识化结束************************");
	}
	
	
	/**
	 * 
	* @Title: initBean  
	* @Description: Bean实例的初始化操作  
	* @param @param clazz
	* @param @return    参数  
	* @return Object    返回类型  
	* @throws
	 */
	private Object initBean(Class<?> clazz){
		Object obj = null;
		try {
			Bean bean = (Bean)clazz.getAnnotation(Bean.class);
			if (bean.single()) {
				String id = bean.value();
				if(beans.containsKey(id)){
					return beans.get(id);
				}
				obj = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					//判断是否需要自动注入
					if (field.isAnnotationPresent(Autowired.class)) {
						Autowired auto = (Autowired)field.getAnnotation(Autowired.class);
						String propName = auto.value();
						//从Bean容器中去取对应的实例
						Object o = getBean(propName);
						if (o == null) {
							//拿到这个属性的class
							Class<?> clazzF = field.getType();
							initBean(clazzF);
							o = getBean(propName);
						}
						field.setAccessible(true);
						field.set(obj, o);
					}
				}
				//放入单例容器
				beans.put(bean.value(), obj);
			}else{
				BeanEntity beanEntity = new BeanEntity();
				beanEntity.setClazz(clazz);
				beanEntity.setId(bean.value());
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					//判断是否需要自动注入
					if (field.isAnnotationPresent(Autowired.class)) {
						Property prop = new Property();
						Autowired auto = (Autowired)field.getAnnotation(Autowired.class);
						String propName = auto.value();
						//从Bean容器中去取对应的实例
						prop.setRef(propName);
						prop.setField(field);
						beanEntity.addProperty(prop);
					}
				}
				prototypes.put(bean.value(), beanEntity);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * 
	* @Title: initController  
	* @Description: 通过控制器类的原型初识化控制器容器  
	* @param @param clazz    参数  
	* @return void    返回类型  
	* @throws
	 */
	private void initController(Class<?> clazz){
		try {
			String className = clazz.getName();	//类的全路径
			//String path = "";	//映射路径
			boolean flag = false;	//标记这个类是否具备方法
			String pPath = "";	//类的映射路径
			//如果类的上面有RequestMapping注解的时候，取得对应的值
			if(clazz.isAnnotationPresent(RequestMapping.class)){
				//取到类上的路径
				pPath = ((RequestMapping)clazz.getAnnotation(RequestMapping.class)).value();
			}
			//获取控制器的所以方法
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				//方法必须具备RequestMapping注解来映射
				if (method.isAnnotationPresent(RequestMapping.class)) {
					//拿上方法的路径拼接起来
					//放入路径映射
					reqs.put(pPath + ((RequestMapping)method.getAnnotation(RequestMapping.class)).value() , method);
					flag = true;
				}
			}
			if(flag){
				Object obj  = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					//判断是否需要自动注入
					if (field.isAnnotationPresent(Autowired.class)) {
						Autowired auto = (Autowired)field.getAnnotation(Autowired.class);
						String propName = auto.value();
						//从Bean容器中去取对应的实例
						Object o = getBean(propName);
						if (o == null) {
							//拿到这个属性的class
							Class<?> clazzF = field.getType();
							initBean(clazzF);
							o = getBean(propName);
						}
						field.setAccessible(true);
						field.set(obj, o);
					}
				}
				controllers.put(className, obj);
			}
		}catch (InstantiationException e) {
				e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public Object getBean(String beanName){
		//先从单例容器中拿
		Object obj = beans.get(beanName);
		if (obj == null) {
			//通过原型去new
			BeanEntity b = prototypes.get(beanName);
			try {
				Class<?> clazz = b.getClazz();
				obj = clazz.newInstance();//单例的实例对象
				for(Property prop : b.getProps()){
					Field field = prop.getField(); 
					field.setAccessible(true);
					field.set(obj,getBean(prop.getRef()));
					
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * 
	* @Title: getExecMethod  
	* @Description: MVC 映射路径 method Controller
	* @param @param path
	* @param @return    参数  
	* @return Method    返回类型  
	* @throws
	 */
	public Method getExecMethod(String path){
		return reqs.get(path);
	}
	
	/**
	 * 
	* @Title: getController  
	* @Description: 获取控制器的实例  
	* @param @param className
	* @param @return    参数  
	* @return BaseController    返回类型  
	* @throws
	 */
	public BaseController getController(String className){
		return (BaseController)controllers.get(className);
	}
}
