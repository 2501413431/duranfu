package com.example.demo.study.MybatisStudy;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */

/**
 * 相当于mybatis 的 MapperProxy 类
 * MapperProxy类中的invoke 调mapperMethod.execute
 * execute中根据具体的 增删改查类型调用sqlSession的增删改查方法
 */
public class TestInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = method.getAnnotation(Select.class).value()[0];
        System.out.println("执行sql语句:" + sql);
        //jdbc的操作
        return null;
    }
}
