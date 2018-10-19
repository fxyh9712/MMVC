package com.fxyh.core.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * 
    * @ClassName: PageData  
    * @Description: 页面数据  
    * @author prosay-fxyh 
    * @date 2017年12月22日  
    *
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class PageData extends HashMap implements Map {

	private Map<String, Object> map = null;
	
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	
	public PageData() {
		map = new HashMap<String, Object>();
	}
	
	public void setData(Map<String, Object> data) {
		this.map = data;
	}
	
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map<String, String[]> reqMaps = request.getParameterMap();
		map = new HashMap<String, Object>();
		Iterator<String> iter = reqMaps.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Object value = reqMaps.get(key);
			if (null == value) {
				map.put(key, "");
			}else if(value instanceof String[]) {
				StringBuffer v = new StringBuffer();
				String[] vs = (String[]) value;
				for (String tmp : vs) {
					//v += tmp + ",";
					v.append(tmp + ",");
				}
				map.put(key, v.substring(0, v.length()-1));
			}else {
				map.put(key, value.toString());
			}
		}
	
	}
	
	/**
	 * 
	    * @Title: hasKey  
	    * @Description: 判断是否存在这个属性  
	    * @param @param key
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public boolean hasKey(String key) {
		return map.containsKey(key);
	}
	
	/**
	 * 
	    * @Title: get  
	    * @Description: 通过key拿到一个对象
	    * @param @param key
	    * @param @return    参数  
	    * @return Object    返回类型  
	    * @throws
	 */
	@Override
	public Object get(Object key) {
		if(map == null) {
			return null;
		}
		return map.get(key);
	}
	/*public Object get(String key) {
		if(map == null) {
			return null;
		}
		return map.get(key);
	}*/
	
	/**
	 * 
	    * @Title: put  
	    * @Description: 往Map中添加值 
	    * @param @param key
	    * @param @param value    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
	/**
	 * 
	    * @Title: getString  
	    * @Description: 通过key拿到字符串  
	    * @param @param key
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public String getString(String key) {
		Object o = map.get(key);
		if (o == null) {
			return "";
		}
		return o.toString();
	}
	
	
}
