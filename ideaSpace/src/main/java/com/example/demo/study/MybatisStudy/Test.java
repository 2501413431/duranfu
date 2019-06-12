package com.example.demo.study.MybatisStudy;

import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        System.out.println(ac.getBean("luban"));
//        System.out.println(ac.getBean("cityDao1"));
        CityDao1 cityDao = (CityDao1)ac.getBean("CityDao1");
        cityDao.query();

    }
}
