package com.fxyh.core.util;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
* @ClassName: XmlUtil  
* @Description: Prosay工具-操作xml的api  解析xml
* @author prosay-fxyh
* @date 2017年12月19日  
* @version 1.0
*
 */

public class XmlUtil {
	
	/**
	 * 
	* @Title: getDocFromStream  
	* @Description: 通过输入流拿到一个document对象  
	* @param @param in
	* @param @return    参数  
	* @return Document    返回类型  
	* @throws
	 */
	public static Document getDocFromStream(InputStream in){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(in);			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 
	* @Title: getRootElement  
	* @Description: 拿根节点  
	* @param @param document
	* @param @return    参数  
	* @return Element    返回类型  
	* @throws
	 */
	public static Element getRootElement(Document document){
		if (document == null) {
			return null;
		}
		Element root = document.getRootElement();
		return root;
	}
	
	/**
	 * 
	* @Title: getChildElements  
	* @Description: 拿到父元素下的所有子元素  
	* @param @param parent	父元素
	* @param @return    参数  
	* @return List<Element>    返回类型  
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChildElements(Element parent){
		if (parent == null) {
			return null;
		}
		return (List<Element>)parent.elements();
	}
	
	public static void log(String log){
		System.out.println(log);
	}
}
