package com.example.demo.study.MybatisStudy;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
public interface CityDao {
    @Select("select * from table where 1 = 1 ")
    public void query();
}
