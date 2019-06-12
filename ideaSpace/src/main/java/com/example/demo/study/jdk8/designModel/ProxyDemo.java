package com.example.demo.study.jdk8.designModel;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/12/24
 *
 *
 * 代理和装饰其实从另一种角度更容易去理解这两个模式的区别：
 * 代理更多的是强调控制对对象的访问，
 * 比如说：访问A对象的查询功能时，访问B对象的更新功能时，
 * 访问C对象的删除功能时都需要判断用户是否登录，
 * 那么我需要将判断用户是否登录功能抽出来，
 * 并对A对象，B对象，C对象进行代理使访问它们时都需要去判断用户是否登录，
 * 简单说就是将某个控制访问权限应用到多个对象上；
 *
 * 而装饰更多的强调现在我要给A对象增加唱歌功能，跳舞功能，说唱功能等等，
 * 简单说就是讲多个功能附加在一个对象上；
 */

public class  ProxyDemo<T extends Animal>{
    public static void main(String[] args) {
        test2();
    }


    public static void test1() {
        Demo demo = new DemoImpl();
        ProxyHandler proxyHandler = new ProxyHandler(demo);
        Demo proxy = (Demo)Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), new Class[]{Demo.class},proxyHandler);
        //只有被代理对象直接调用的方法才能被代理
        proxy.test1();
//        proxy.test2();
    }

    public static void test2() {
        Animal dog = new Dog();
        ProxyHandler proxyHandler = new ProxyHandler(dog);
        Animal proxy = (Animal)Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), new Class[]{Animal.class},proxyHandler);
        proxy.say();
    }

    public void test3(T obj) {
        ProxyHandler proxyHandler = new ProxyHandler(obj);
        T proxy = (T)Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), new Class[]{Animal.class},proxyHandler);
        proxy.say();
    }
}

class ProxyHandler implements InvocationHandler {

    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //代理对象自己的业务
        //额外的处理
        System.out.println("========加入了额外的处理======");
        //日志记录
        //权限控制
        //事务控制
        //.......
        return method.invoke(target, args);
    }
}

class DemoImpl implements Demo {
    @Override
    public void test1() {
        System.out.println("======11111111111========");
        this.test2();//没有被代理   //this 是DempImpl的对象实例
    }

    @Override
    public void test2() {
        System.out.println("========222222222========");
    }
}

interface Demo {
    void test1();
    void test2();
}









class MyHandler implements InvocationHandler {

    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //做些额外的事情
        System.out.println("======额外的处理======");
        return method.invoke(target, args);
    }
}

class Dog implements Animal {

    @Override
    public void say() {
        System.out.println("汪汪汪");
    }

    @Override
    public void eat() {
        System.out.println("吃骨头");
    }
}

class Cat implements Animal {

    @Override
    public void say() {
        System.out.println("喵喵喵");
    }

    @Override
    public void eat() {
        System.out.println("吃小鱼");
    }
}


interface Animal {
    void say();
    void eat();
}




















