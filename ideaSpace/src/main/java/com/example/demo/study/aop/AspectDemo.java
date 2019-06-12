package com.example.demo.study.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
@Component
@Aspect
public class AspectDemo {

    @Pointcut("execution(* com.example.demo.study.aop..*.*(..))")
    public void inServiceLayer() {}

    @Before("inServiceLayer()")
    public void doAccessCheck() {
        // ...
        System.out.println("aop之前----------------");
    }
}
