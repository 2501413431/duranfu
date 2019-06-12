package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.GlobalResult;
import com.example.demo.service.SpringContextHolder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射统一访问接口
 * Created by admin on 2018/7/27.
 */
@Controller
@RequestMapping("app")
@Log
public class AppController {
//	@Autowired
//	@Qualifier("primaryJdbcTemplate")
//	protected JdbcTemplate jdbcTemplate1;
//
//	@Autowired
//	@Qualifier("secondaryJdbcTemplate")
//	protected JdbcTemplate jdbcTemplate2;

	public static void main(String[] args) {
		jsonTest();
	}

	@RequestMapping("toIndex")
	public String test(String a) {
		log.info("测试traceId用");
//		jdbcTemplate1.update("insert into usera(name,age,gender) values(?, ?, ?)", "aaa", 21, 0);
//		jdbcTemplate2.update("insert into usera(name,age,gender) values(?, ?, ?)",  "bbb", 21,1);
		return "index";
	}

	public static void jsonTest() {
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("name", "哈哈");

		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("name", "嘻嘻");

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);

		GlobalResult result = new GlobalResult();
		result.setCode("200");
		result.putData("list",list);
		String json = JSON.toJSONString(result);
		System.out.println("json" + json);

		Map<String, Object> sReturn = JSONObject.parseObject(json);
		Map<String, Object> data1 = (Map<String, Object>)sReturn.get("data");

		List<Map<String, Object>> data = (List<Map<String, Object>>)data1.get("list");
		data.forEach(info -> System.out.println(info.get("name")));

	}



	@RequestMapping("get1")
	public void get1(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		JSONObject jsonObject = JSON.parseObject(jsonData);
		String serviceName = jsonObject.get("service").toString();
		String methodName = jsonObject.get("method").toString();
		System.out.print("服务名:" + serviceName);
		System.out.print("方法名:" + methodName);
		Object bean = SpringContextHolder.getBean(serviceName);
		Class<?> clazz = bean.getClass();
		Method method;
		try {
			method = clazz.getDeclaredMethod(methodName,String.class);
			method.invoke(bean, jsonData);
			System.out.print("执行成功");
		} catch (Exception e) {

		}

	}
}
