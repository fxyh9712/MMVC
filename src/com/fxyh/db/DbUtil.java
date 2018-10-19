package com.fxyh.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 
* @ClassName: DbUtil  
* @Description: 数据库工具类（用于获取连接和释放连接）
* @author prosay-Jame
* @date 2017年12月19日  
*
 */
public class DbUtil {
//数据源属性
private static DruidDataSource dataSource;
private static Properties dbConfig;
//定义一个线程的本地对象（对同一个线程内是共享的）
//它的作用域范围只有当前线程
private static final ThreadLocal<Connection> CONN_LOCAL =  new ThreadLocal<Connection>();
	static{
		//加载dbConfig读取配置文件
		try {
			dbConfig = new Properties();
			dbConfig.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(dbConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getConnection(){
		try{
			Connection conn = CONN_LOCAL.get();//先从本地线程变量
			if(conn == null){
				conn = dataSource.getConnection();
				CONN_LOCAL.set(conn);//将数据库连接对象放到本地线程变量
			}
			return conn;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 开启事务
	 */
	public static void openTransaction(){
		try{
			//设置自动提交为false
			getConnection().setAutoCommit(false);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 提交事务
	 * */
	public static void commitTransaction(){
		try{
			//提交事务
			getConnection().commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction(){
		try{
			//回滚事务
			getConnection().rollback();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 关闭资源
	 */
	public static void close(){
		try {
			getConnection().close();
			CONN_LOCAL.remove();//清空本地的线程变量
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: release  
	* @Description: 释放数据源
	* @param     参数  
	* @return void    返回类型  
	* @throws
	 */
	public static void release(){
		dataSource.close();
	}
}
