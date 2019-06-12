package com.example.demo.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/2/27
 */
public class Proxy {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
//        Subject s = (Subject)proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, proxy);
//        s.visit();
    }
}

/**
 * 代理模式的作用（为什么使用代理模式）
 * 1、中介隔离作用
 * 2、开闭原则，增强功能
 */

/**
 * 动态代理：
 * 根据代理的对象，动态创建代理类，通过反射实现的，
 * 借助java自带的java.lang.reflect.Proxy通过固定规则生成
 * 1.编写一个委托类的接口，即静态代理的（Subject接口）
 * 2.实现一个真正的委托类，即静态代理的（RealSubject类）
 * 3.创建一个动态代理类，实现InvocationHandler接口并重写invoke方法
 * 4.在测试类中生成动态代理的对象。
 */

class DynamicProxy implements InvocationHandler {
    private Object object;
    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }
}



/**
 * 静态代理
 */
interface Subject {
    void visit();
}

class RealSubject implements Subject {

    @Override
    public void visit() {
        System.out.println("目标类的方法");
    }
}

class ProxySubject implements Subject {

    private RealSubject real;

    public ProxySubject(RealSubject real) {
        this.real = real;
    }

    @Override
    public void visit() {
        System.out.println("增强方法前");
        real.visit();
        System.out.println("增强方法后");
    }
}
