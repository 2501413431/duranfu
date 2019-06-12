package com.example.demo.mapper;
import com.example.demo.entity.AaaInfo;

public interface AaaInfoMapper {
    int insert(AaaInfo record);

    int insertSelective(AaaInfo record);

    AaaInfo selectByName(String name);

    int updateByname(AaaInfo record);
}