package com.example.demo.study.MybatisStudy;

import com.example.demo.study.aop.IndexService;
import com.example.demo.study.aop.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * BeanFactoryPostProcessor能修改SpringBean   beandefinition不能新增defination
 *ImportBeanDefinitionRegistrar 可以新增（参数BeanDefinitionRegistry）
 */



/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
@Configuration
@ComponentScan("com.example.demo.study.MybatisStudy")
@EnableLuban
public class AppConfig {
//
}
