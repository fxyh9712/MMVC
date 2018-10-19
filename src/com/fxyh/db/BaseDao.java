package com.fxyh.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fxyh.core.annotation.Bean;
import com.fxyh.core.entity.PageData;


/**
 * 
* @ClassName: BaseDao  
* @Description: 数据库操作的封装类 
* @author prosay-Jame
* @date 2017年12月19日  
*
 */
@Bean("baseDao")
public class BaseDao {
	/**
	 * 用一个map对象作为数据库的一行记录，列名作为map的key，值最为map的value
	 * @param sql
	 * @param params
	 * @return
	 */
	public  List<Map<String,Object>> queryForList(String sql,List<Object> params){
		//这里new出来一个集合对象，这个集合中只能存储Map<String,Object>
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = DbUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			setParameters(pstm,params);
			rs = pstm.executeQuery();
			//元数据对象 包含结果集的结构
			ResultSetMetaData rmd = rs.getMetaData();
			int num = rmd.getColumnCount();//获取结果集有多少列
			//
			Map<String,Object> obj ;
			while(rs.next()){//这个while循环循环一次是不是一个对象
				obj = new HashMap<String,Object>();
				for(int i = 1 ; i <= num ; i++){
					//根据列的状态获取列名
					String columnLabel = rmd.getColumnLabel(i);
					obj.put(columnLabel, rs.getObject(i));
				}
				//集合添加对象为何放在这里
				result.add(obj);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			DbUtil.close();
		}
		return result;
	}
	/**
	 * 通过PRowMapper对象的mappingRow方法来决定每一行对应的对象
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	public <E> List<E> queryForList(PRowMapper<E> mapper,String sql,List<Object> params){
		//这里new出来一个集合对象，这个集合中只能存储Map<String,Object>
				List<E> result = new ArrayList<E>();
				Connection conn  = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				try{
					conn = DbUtil.getConnection();
					pstm = conn.prepareStatement(sql);
					setParameters(pstm,params);
					rs = pstm.executeQuery();
					int rownum = 0;
					while(rs.next()){//这个while循环循环一次是不是一个对象
						rownum++;
						result.add(mapper.mappingRow(rs, rownum));
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}finally {
					DbUtil.close();
				}
				return result;
	}
	public <E> List<E> queryForList(Class<E> clazz,String sql,List<Object> params){
		//这里new出来一个集合对象，这个集合中只能存储Map<String,Object>
				List<E> result = new ArrayList<E>();
				Connection conn  = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				try{
					conn = DbUtil.getConnection();
					pstm = conn.prepareStatement(sql);
					setParameters(pstm,params);
					rs = pstm.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					int num = metaData.getColumnCount();
					E obj;
					Method[] methods = clazz.getMethods();
					Map<String,Method> property = new HashMap<String,Method>();
					Method method ;
					for(int i = 0 ;i < methods.length ; i++){
						method = methods[i];
						String methodName = method.getName();
						if(methodName.startsWith("set")){
							property.put(methodName.substring(3).toUpperCase(),method);
						}
					}
					while(rs.next()){//这个while循环循环一次是不是一个对象
						//通过类对象 创建这个类的实例 必须提供空构造
						obj = clazz.newInstance();
						
						for(int i = 1 ;i <= num ; i++){
							String columnName = metaData.getColumnLabel(i);
							if(property.containsKey(columnName)){
								method = property.get(columnName);
								method.invoke(obj, rs.getString(i));
							}
						}
						result.add(obj);
						
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}finally {
					DbUtil.close();
				}
				return result;
	}
	public Map<String,Object> queryForObject(String sql ,Object...params){
		//这里new出来一个集合对象，这个集合中只能存储Map<String,Object>
		 Map<String,Object> result = new HashMap<String,Object>();
				Connection conn  = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				try{
					conn = DbUtil.getConnection();
					pstm = conn.prepareStatement(sql);
					//设置参数
					if(params!=null){
						for(int i = 0 ; i < params.length ; i++){
							//通用设置参数
							pstm.setObject(i+1, params[i]);
						}
					}
					rs = pstm.executeQuery();
					//元数据对象 包含结果集的结构
					ResultSetMetaData rmd = rs.getMetaData();
					int num = rmd.getColumnCount();//获取结果集有多少列
					
					if(rs.next()){//这个while循环循环一次是不是一个对象
						
						for(int i = 1 ; i <= num ; i++){
							//根据列的状态获取列名
							String columnLabel = rmd.getColumnLabel(i);
							result.put(columnLabel, rs.getObject(i));
						}
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}finally {
					DbUtil.close();
				}
				return result;
	}
	public <E> E queryForObject(PRowMapper<E> mapper,String sql,List<Object> params){
		//这里new出来一个集合对象，这个集合中只能存储Map<String,Object>
				E result = null;
				Connection conn  = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				try{
					conn = DbUtil.getConnection();
					pstm = conn.prepareStatement(sql);
					setParameters(pstm,params);
					rs = pstm.executeQuery();
					int rownum = 0;
					if(rs.next()){//这个while循环循环一次是不是一个对象
						rownum++;
						result = mapper.mappingRow(rs, rownum);
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}finally {
					DbUtil.close();
				}
				return result;
	}
	/**
	 * 更新数据方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int excuteUpdate(String sql,List<Object> params){
		Connection conn = null;
		PreparedStatement pstm = null;
		int result = 0;
		try{
			conn = DbUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			setParameters(pstm,params);
			result = pstm.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}finally {
			DbUtil.close();
		}
		return result;
	}
	/**
	 * 执行批量更新
	 * @param sql
	 * @param params
	 */
	public void executeBatch(String sql,List<List<Object>> params){
		try{
			Connection conn = DbUtil.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);
			for(List<Object> param : params){
				setParameters(pre,param);
				pre.addBatch();
			}
			pre.executeBatch();
			//pre.clearBatch();//当做多次批处理清空列表
			//int i = 1/0;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}finally {
			DbUtil.close();
		}
	}
	
	/**
	 * 
	    * @Title: getTotal  
	    * @Description: 查询总记录数  
	    * @param @param sql
	    * @param @param params
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	public int getTotal(String sql, List<Object> params) {
		int result = 0;
		Connection conn = DbUtil.getConnection();
		PreparedStatement pr;
		try {
			pr = conn.prepareStatement(sql);
			setParameters(pr, params);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<PageData> queryForPage(String sql,List<Object> params){
		List<PageData> result = new ArrayList<PageData>();
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = DbUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			setParameters(pstm,params);
			rs = pstm.executeQuery();
			//元数据对象 包含结果集的结构
			ResultSetMetaData rmd = rs.getMetaData();
			int num = rmd.getColumnCount();//获取结果集有多少列
			//
			PageData obj ;
			while(rs.next()){//这个while循环循环一次是不是一个对象
				obj = new PageData();
				for(int i = 1 ; i <= num ; i++){
					//根据列的状态获取列名
					String columnLabel = rmd.getColumnLabel(i);
					//Object o = rs.getObject(i);
					//System.out.println(o.toString());
					obj.put(columnLabel, rs.getObject(i));
				}
				//集合添加对象为何放在这里
				result.add(obj);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			DbUtil.close();
		}
		return result;
	}
	
	/**
	 * 设置预处理语句的sql参数
	 * @param pstm
	 * @param params
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement pstm,List<Object> params) throws SQLException{
	
			//设置参数
			if(params!=null){
				for(int i = 0 ; i < params.size() ; i++){
					//通用设置参数
					pstm.setObject(i+1, params.get(i));
				}
			}
	}

}
