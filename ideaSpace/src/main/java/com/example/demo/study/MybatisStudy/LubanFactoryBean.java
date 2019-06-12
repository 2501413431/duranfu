package com.example.demo.study.MybatisStudy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/19
 */

/***
 * FactoryBean是spring整合mybatis的关键点
 * mybatis在 MapperFactoryBean 中也实现了factoryBean,
 * 他的getObject的方法中 getSqlSession().getMapper(this.mapperInterface);
 * 在getMapper方法中利用jdk动态代理创建mapper的代理对象返回
 */
public class LubanFactoryBean<T>  implements FactoryBean {

    Class mapperInterface;

    public LubanFactoryBean(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {

        Class[] classes = {mapperInterface};
        TestInvocationHandler invocationHandler = new TestInvocationHandler();
        T cityDao = (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), classes, invocationHandler);
        return cityDao;
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
