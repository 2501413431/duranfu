package com.example.demo.study.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        IndexService indexService = (IndexService)ac.getBean(IndexService.class);
        IndexService indexService1 = (IndexService)ac.getBean(IndexService.class);
        System.out.println(indexService);
        System.out.println(indexService1);

    }
}
