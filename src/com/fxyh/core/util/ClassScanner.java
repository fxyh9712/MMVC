package com.fxyh.core.util;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
* @ClassName: ClassScanner  
* @Description: 扫描制定包名下的类，并返回集合  
* @author prosay-fxyh
* @date 2017年12月19日  
*
 */
public class ClassScanner {
	
	/**
	 * 
	* @Title: scannerClass  
	* @Description: 根据传入的包路径，扫描包下的所有类  
	* @param @param packagePath
	* @param @return    参数  
	* @return Map<String,Class<?>>    返回类型  
	* @throws
	 */
	public static Map<String, Class<?>> scannerClass(String packagePath){
		//把点替换成/
		String filePath = packagePath.replace(".", "/");
		//System.out.println(filePath);
		Map<String, Class<?>> result = new HashMap<String, Class<?>>();
		//取得给定路径的所有URL地址枚举对象
		try {
			//通过包路径获得多个URL的对象集合 	类路径和jar包都存在的情况下
			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(filePath);
			String rootPath = filePath;
			//System.out.println(rootPath);
			//迭代这个包名包含的所有的类路径（jar）
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				//通过url去判断这个是文件对象(文件夹)才进入这个操作
				if ("file".equals(url.getProtocol())) {	//类路径的处理方式
					//System.out.println(url.getPath());
					File folder = null;
					if(System.getProperty("os.name").toLowerCase().startsWith("win")){  
						folder = new File(url.getPath().substring(1));//Windows操作系统
					}else if(System.getProperty("os.name").toLowerCase().startsWith("linux")) {
						folder = new File(url.getPath());//Linux操作系统
					}
					//扫描文件夹下的所有类（包括子包-子文件夹）
					scannerFile(folder, rootPath, result);
				}else if("jar".equals(url.getProtocol())){	//jar包的处理方式
					//JarURLConnection
					JarURLConnection connection = (JarURLConnection)url.openConnection();
					if(connection != null){
						//通过连接对象获取一个jar文件对象
						JarFile jarFile = connection.getJarFile();
						if (jarFile != null ) {
							Enumeration<JarEntry> jarEntrys = jarFile.entries();
							while(jarEntrys.hasMoreElements()){
								//jar包中的实体
								JarEntry jarEntry = jarEntrys.nextElement();
								//System.out.println(jarEntry.getName());
								String entryName = jarEntry.getName();
								//筛选不需要 留下需要的
								if (entryName.endsWith(".class") && entryName.startsWith(filePath)){
									//System.out.println(entryName);
									String className = entryName.split("\\.")[0];
									className = className.replace("/", ".");
									result.put(className, Class.forName(className));
								}
							}
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			XmlUtil.log(e.getMessage() + "初始化异常！");
		}
		
		return result;
	}
	
	/**
	 * 
	* @Title: scannerFile  
	* @Description: 扫描文件夹下的所有的java类  
	* @param @param folder
	* @param @param rootPath
	* @param @param result    参数  
	* @return void    返回类型  
	* @throws
	 */
	private static void scannerFile(File folder,String rootPath,Map<String, Class<?>> result ){
		//获取当前folder文件夹下的所有的子文件（目录）
		File[] files = folder.listFiles();
		for (File file : files) {
			if(file.isDirectory()){//如果是文件夹，则继续深入
				//递归调用自己
				scannerFile(file , rootPath + "/" + file.getName(), result);
			}else{//是文件的情况下
				//拿到当前文件的真实路径
				String path = file.getAbsolutePath();
				//判断是否是以.class结尾的文件
				if (path.endsWith(".class")) {
					//E:\DemoList\JavaWeb\Workspace\MyMVC\build\classes\com\prosay\core\entity\Bean.class
					//把反斜杠换成正斜杠
					path = path.replace("\\", "/");
					//System.out.println(path);
					//获得类路径	com/prosay/core/ApplicationContext
					String className = rootPath + path.substring(path.lastIndexOf("/"), path.indexOf(".class"));
					try {
						//将路径符换成点
						className = className.replace("/", ".");
						//System.out.println(className);
						result.put(className, Class.forName(className));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		//System.out.println(scannerClass("com.fxyh.core"));
		//scannerClass("org.dom4j");
	}
	
}
