package com.example.demo.study.MybatisStudy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * BeanFactoryPostProcessor只能修改类的定义，没办法新增,新增得用ImportBeanDefinitionRegistrar
 * BeanFactoryPostProcessor和BeanPostProcessor都是Spring初始化bean时对外暴露的扩展点
 * SpringIoc容器允许BeanFactoryPostProcessor在容器实例化任何Bean之前读取bean的定义（配置元数据），并可以修改他。
 * 同时可以定义多个BeanFactoryPostProcessor,通过设置order属性来确定各个BFPP的执行顺序。
 * 重写postProcessBeanFactory方法后通过beanFactory可以获取bean的定义信息，并可以修改bean的定义信息
 *
 * BeanPostProcessor有postProcessBeforeInitialization和postProcessAfterInitialization
 * @Auth duranfu
 * @Date 2019/5/25
 */
//@Component
//public class LubanFactoryPostProcessor implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        GenericBeanDefinition cityDao1 = (GenericBeanDefinition)configurableListableBeanFactory.getBeanDefinition("cityDao1");
//        cityDao1.setBeanClass(CityDao.class);
//
//    }
//}
