package com.fxyh.controller;

import java.util.List;
import java.util.Map;

import com.fxyh.core.BaseController;
import com.fxyh.core.CustomView;
import com.fxyh.core.annotation.Autowired;
import com.fxyh.core.annotation.Controller;
import com.fxyh.core.annotation.RequestMapping;
import com.fxyh.core.entity.PageData;
import com.fxyh.service.StudentService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired("studentService")
	private StudentService studentService;
	
	@RequestMapping("/index")
	public CustomView index() {
		CustomView cv = new CustomView("test/index");
		return cv;
	}
	
	/**
	 * 测试查询通过id 查询student信息
	 * @return
	 */
	@RequestMapping("/test")
	public CustomView test() {
		CustomView cv = new CustomView("test/test");
		PageData pd = this.getPageData();
		List<Map<String, Object>> students = studentService.queryStudents(pd);
		put("students", students);
		return cv;
	}
	
}
