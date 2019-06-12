//package com.example.demo.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.example.demo.common.Exception.BusinessException;
//import com.example.demo.common.GlobalResult;
//import com.example.demo.common.JedisConfig;
//import com.example.demo.service.TestService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import javax.annotation.Resource;
//import java.util.*;
//import java.util.concurrent.Callable;
//
///**
// * Created by admin on 2018/7/29.
// */
//@Controller
//@RequestMapping("test")
//@Slf4j
//public class TestController {
//
//	@Resource
//	private JedisPool JedisPool;
//
//	@Resource
//	private TestService testService;
//
//
//	@RequestMapping("toIndex")
//	public String test(String a) {
//		return "index";
//	}
//
//
////	@RequestMapping("mybatisTest")
////	@ResponseBody
////	public GlobalResult mybatisTest(String a) throws BusinessException {
////		return testService.mybatisTest();
////	}
//
//	@RequestMapping("exeptionTest")
//	public GlobalResult exeptionTest(String a) throws BusinessException {
//			return testService.test();
//	}
//
////	@RequestMapping("mqTest")
////	@ResponseBody
////	public GlobalResult mqTest(String a) throws BusinessException {
////		return testService.mqTest("这是我发送的消息");
////	}
//
////	@RequestMapping("jedisTest")
////	public void jedisTest() {
////		try (Jedis jedis = JedisPool.getResource()) {
////			String setex = jedis.setex("123456", 1000, "123");
////			System.out.println(setex);
////		}
////	}
//
//	@RequestMapping("springCatchTest")
//	@ResponseBody
//	public GlobalResult springCatchTest(String name) {
//		GlobalResult res = testService.springCatchTest(name);
//		return res;
//	}
//
//	@RequestMapping("springCatchTest1")
//	@ResponseBody
//	public GlobalResult springCatchTest1(String name) {
//		GlobalResult res = testService.springCatchTest1(name);
//		return res;
//	}
//
//
//
//
//
//	public static void main(String[] args) {
//		Map<String, Object> requestMap = new HashMap<String, Object>();
//		List<Object> permanentAddressList = new ArrayList<Object>();//常住地址
//		Map<String, Object> permanentAddress = new HashMap<String, Object>();
//		permanentAddress.put("area", "value");
//		permanentAddress.put("province", "value");
//		permanentAddress.put("city", "value");
//		permanentAddress.put("detailAddress", "value");
//		Map<String, Object> permanentAddressMap = new HashMap<String, Object>();
//		permanentAddressMap.put("permanentAddress", permanentAddress);
//		permanentAddressList.add(permanentAddressMap);
//		requestMap.put("permanentAddressList", permanentAddressList);
//		System.out.println(JSON.toJSONString(requestMap));
//	}
//
//}
