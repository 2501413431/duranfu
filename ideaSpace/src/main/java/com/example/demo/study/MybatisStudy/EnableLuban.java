package com.example.demo.study.MybatisStudy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(LubanRegister.class)
public @interface EnableLuban {
}
