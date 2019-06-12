package com.example.demo.study.MybatisStudy;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
public interface CityDao1 {
    @Select("select * from aaa where 2 = 2")
    public void query();

}
