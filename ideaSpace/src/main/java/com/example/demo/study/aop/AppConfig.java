package com.example.demo.study.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
//@Configuration
//@ComponentScan("com.example.demo.study.aop")
//@EnableAspectJAutoProxy
public class AppConfig {
//    @Bean
    public IndexService indexService() {
        return new IndexService();
    }

//    @Bean
    public TestService testService() {
        indexService();
        return new TestService();
    }
}
