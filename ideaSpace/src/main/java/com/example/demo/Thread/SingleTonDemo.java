package com.example.demo.Thread;

import com.alibaba.fastjson.JSON;

import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.sql.Connection;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/11/5
 */
public class SingleTonDemo {
    public static void main(String[] args) {
        String s = new String("0.00050");
        Double aDouble = Double.parseDouble(s);
        Double b = aDouble*100;
        String s1 = b.toString();
        System.out.println(s1);


//        Thread1 t1 = new Thread1();
//        Thread1 t2 = new Thread1();
//        Thread1 t3 = new Thread1();
//        t1.start();
//        t2.start();
//        t3.start();
    }
}

enum single4 {
    connectionFactory;
    private Connection connection;
    private single4() {

    }
}

class Single3 {
    private static Single3 single3;
    public Single3() {
    }
    static {
        single3 = new Single3();
    }
    public static Single3 getInstance() {
        return single3;
    }
}

/**
 * 静态内置类实现单利模式
 */
class Single2 {
    private static class SingleTon2Holder {
        private static Single2 single2 = new Single2();
    }
    private Single2() {}
    public static Single2 getInstance() {
        return SingleTon2Holder.single2;
    }
    protected Object raadResolve() throws ObjectStreamException{
        System.out.println("调用了readResolve方法");
        return SingleTon2Holder.single2;
    }
}


/**
 * DCL双检查锁机制 懒汉式单例模式
 */
class SingleTon1 {
    private static SingleTon1 singleTonDemo;
    private SingleTon1() {
    }
    public static SingleTon1 getInstance() {
        try {
            if (singleTonDemo != null) {
            } else {
                Thread.sleep(3000);
                synchronized (SingleTonDemo.class) {
                    if (singleTonDemo == null) {
                        singleTonDemo = new SingleTon1();
                    }
                }
            }
        } catch (Exception e) {
        }
        return singleTonDemo;
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Single2.getInstance().hashCode());
    }
}
