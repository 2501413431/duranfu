package com.example.demo.service;

import com.example.demo.common.Exception.BusinessException;
import com.example.demo.common.GlobalResult;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/26
 */
public interface TestService {
    public GlobalResult test()  throws BusinessException;

    GlobalResult mqTest(String content);

    GlobalResult mybatisTest();

    GlobalResult springCatchTest(String name);

    GlobalResult springCatchTest1(String name);
}
