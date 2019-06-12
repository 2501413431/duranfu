package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/19
 * 工厂模式：
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类，工厂方法使一个类的实例化延迟到其子类
 *
 * 优点：工厂方法模式是典型的解耦框架，高层模块值需要知道产品的抽象类，其他的实现都不用关心
 *
 */

public class FactoryModel {

    public static void main(String[] args) {
        String a = "aaa";
        System.out.println(a.hashCode());
    }










    public static void test() {
        Creator c = new Creator1();
        Product p = c.createProduct(Product1.class);
    }
}




//不用这个类的话就是简单工厂模式（静态工厂模式）
abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}

class Creator1 extends Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product p = null;
        try {
            p = (Product)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {

        }
        return (T)p;
    }
}

//抽象产品类
abstract class Product {
    //产品类的公用方法
    public void method1() {

    }
    //抽象方法
    public abstract void method2();
}

class Product1 extends Product {
    @Override
    public void method2() {

    }
}

class Product2 extends Product {
    @Override
    public void method2() {

    }
}

