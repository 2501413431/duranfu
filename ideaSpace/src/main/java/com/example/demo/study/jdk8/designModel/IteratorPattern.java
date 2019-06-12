package com.example.demo.study.jdk8.designModel;

import java.util.*;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/21
 *
 * 迭代器模式：
 * 目前是一个没落的模式，基本上没人会单独写一个迭代器，除非是产品性质的开发
 * 它提供一种方法访问一个容器对象中各个元素，而又不暴露该对象的内部细节
 */
public class IteratorPattern {
    public static void main(String[] args) {

    }
}

class IteratorImpl implements Iterator {

    //定义当前游标
    public int coursor = 0;

    private Vector vector = new Vector();
    public IteratorImpl(Vector vector) {
        this.vector = vector;
    }

    //判断是否到达尾部
    @Override
    public boolean hasNext() {
        if (this.coursor == vector.size()) {
            return  false;
        } else {
            return true;
        }
    }

    //返回下一个元素
    @Override
    public Object next() {
        Object result = null;
        if (this.hasNext()) {
            result = this.vector.get(this.coursor++);
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public void remove() {
        this.vector.remove(this.coursor);
    }
}