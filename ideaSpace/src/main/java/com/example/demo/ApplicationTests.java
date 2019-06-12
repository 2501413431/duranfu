//package com.example.demo;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class ApplicationTests {
//
//    @Autowired
//    @Qualifier("primaryJdbcTemplate")
//    protected JdbcTemplate jdbcTemplate1;
//
//    @Autowired
//    @Qualifier("secondaryJdbcTemplate")
//    protected JdbcTemplate jdbcTemplate2;
//
////    @Before
////    public void setUp() {
////        jdbcTemplate1.update("DELETE  FROM  USER ");
////        jdbcTemplate2.update("DELETE  FROM  USER ");
////    }
//
//    @Test
//    public void test() throws Exception {
//
//        jdbcTemplate1.update("insert into usera(name,age,gender) values(?, ?, ?)", "aaa", 21, 0);
//
//        jdbcTemplate2.update("insert into usera(name,age,gender) values(?, ?, ?)",  "bbb", 21,1);
//
//    }
//
//
//}