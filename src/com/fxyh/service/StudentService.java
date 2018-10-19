package com.fxyh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fxyh.core.annotation.Autowired;
import com.fxyh.core.annotation.Bean;
import com.fxyh.core.entity.PageData;
import com.fxyh.db.BaseDao;

@Bean("studentService")
public class StudentService {

	@Autowired("baseDao")
	private BaseDao baseDao;
	
	public List<Map<String,Object>> queryStudents(PageData pd){
		String sql="SELECT id,sname from student ";
		String condition="";
		List<Object> params =new  ArrayList<Object>();
		if(pd.hasKey("sname")){
			condition+=" where sname like ?";
			params.add("%"+pd.get("sname")+"%");
		}
		System.out.println(sql+condition);
		List<Map<String,Object>> results = baseDao.queryForList(sql + condition, params);
		return results;
	}
	
}
