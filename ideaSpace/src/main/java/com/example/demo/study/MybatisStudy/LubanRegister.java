package com.example.demo.study.MybatisStudy;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */

/**
 * MapperScannerRegistrar 里的 doScan
 */
public class LubanRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Class[] classes = new Class[]{CityDao.class, CityDao1.class};//扫描出来
        for (Class clazz:classes) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanDefinitionBuilder.getBeanDefinition();
            beanDefinition.setBeanClass(LubanFactoryBean.class);//修改beanClass
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz.getName());//设置默认构造方法
            beanDefinitionRegistry.registerBeanDefinition(clazz.getSimpleName(),beanDefinition);
        }
    }
}
