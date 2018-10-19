package com.fxyh.entity;

import java.util.List;
import java.util.Map;

import com.fxyh.db.BaseDao;

public class User {

	private String userName;
	private String password;
	private BaseDao baseDao;
	
	public void query(){
		List<Map<String, Object>> queryForList = baseDao.queryForList("select * from course", null);
		System.out.println(queryForList);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
