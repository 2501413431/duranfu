package com.example.demo.study.aop;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/18
 */
public class IndexService {
    public IndexService() {
        System.out.println("indexService---init");
    }

    public void query() {
        System.out.println("indexService---service---query");
    }
}
