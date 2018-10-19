package com.fxyh.core.entity;

import java.util.List;


/**
 * 
    * @ClassName: Page  
    * @Description: 分页相关属性，分页实体  
    * @author prosay-fxyh 
    * @date 2017年12月22日   
    *
 */
public class Page<T> {

	private int currentPage = 1;	//当前页
	private int total;				//总记录数
	private int pageNum;			//总页数
	private int pageSize;			//每页条数
	private PageData pd;			//查询参数
	private List<T> data;			//数据列表
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		pageNum = total/pageSize;
		if (total%pageSize != 0) {
			pageNum++;
		}
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public PageData getPd() {
		return pd;
	}
	public void setPd(PageData pd) {
		this.pd = pd;
		if (pd != null) {
			String curPage = pd.getString("currentPage");
			String size = pd.getString("pageSize");
			if ("".equals(curPage)) {
				currentPage = 1;
			}else {
				currentPage = Integer.valueOf(curPage);
			}
			if ("".equals(size)) {
				pageSize = 10;
			} else {
				pageSize = Integer.valueOf(size);
			}
		}
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	/**
	 * 
	    * @Title: getPageSql  
	    * @Description: 基于mysql的分页语句  
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public String getPageSql() {
		return " limit " + (this.currentPage-1)*pageSize + "," + pageSize;
	}
	
}
