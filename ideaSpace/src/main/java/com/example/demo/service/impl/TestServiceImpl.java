//package com.example.demo.service.impl;
//
//import com.example.demo.mapper.AaaInfoMapper;
//import com.example.demo.common.GlobalResult;
//import com.example.demo.entity.AaaInfo;
////import com.example.demo.rabbitMq.MsgProducer;
//import com.example.demo.service.TestService;
//import com.example.demo.common.Exception.BusinessException;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2019/4/26
// */
//@Service
//public class TestServiceImpl implements TestService {
//
//    @Resource
//    private MsgProducer msgProducer;
//
//    @Resource
//    private AaaInfoMapper aaaInfoMapper;
//    @Override
//    public GlobalResult test() throws  BusinessException{
//        System.out.println("service--->test：开始执行");
//
//        try {
//            int a = 2/0;
//        } catch (Exception e) {
//            System.out.println("service--->test：执行异常" + "/n" + e);
//            throw new BusinessException("400", "service的test方法异常");
//        }
//
//        System.out.println("service--->test：执行完毕");
//        return new GlobalResult("200");
//    }
//
//    @Override
//    public GlobalResult mqTest(String content) {
//        try {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("aaa", "AAA");
//            map.put("bbb", "BBB");
//            msgProducer.sendMsg(content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new GlobalResult("200");
//    }
//
//    @Override
//    public GlobalResult mybatisTest() {
//        AaaInfo aaaInfo = new AaaInfo();
//        aaaInfo.setCourse("体育");
//        aaaInfo.setName("小红");
//        aaaInfo.setScore(55);
//        aaaInfoMapper.insert(aaaInfo);
//        return new GlobalResult("200");
//    }
//
//
//    /**
//     * 1、对于使用@Cacheable标注的方法，spring在每次执行前都会检查Cache中是否存在相同key的缓存
//     *    如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回。
//     *
//     * 2、Cacheable可以标记在一个方法上，也可以标记在一个类上
//     *    spring在缓存方法的返回值时是以键值对进行缓存的。
//     *
//     * 3、有时候并不希望缓存一个方法所有的返回结果，通过condition属相实现
//     *    比如： @Cacheable(value={"users"}, key="#user.id", condition="#user.id%2==0")
//     *
//     */
//    @Cacheable(value = "score", key = "#name")
//    @Override
//    public GlobalResult springCatchTest(String name) {
//        AaaInfo aaaInfo = aaaInfoMapper.selectByName(name);
//        GlobalResult result = new GlobalResult();
//        result.setCode("200");
//        result.putData("userInfo", aaaInfo);
//        return result;
//    }
//
//    /**
//     * CacheEvict 修改时清空对应的缓存   value和key要和 Cacheable对应
//     */
//    @CacheEvict(value = "score", key = "#name")
//    @Override
//    public GlobalResult springCatchTest1(String name) {
//        AaaInfo aaaInfo = new AaaInfo();
//        aaaInfo.setCourse("体育");
//        aaaInfo.setName(name);
//        aaaInfo.setScore(55);
//        aaaInfoMapper.updateByname(aaaInfo);
//        GlobalResult result = new GlobalResult();
//        result.setCode("200");
//        result.putData("userInfo", aaaInfo);
//        return result;
//    }
//
//
//}
