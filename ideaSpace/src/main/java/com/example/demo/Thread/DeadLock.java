package com.example.demo.Thread;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/3
 */
public class DeadLock {
    public static void main(String[] args) {
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(null,"aaa");
//        System.out.println(map.get(null));
    }
    private static String A = "A";
    private static String B = "B";
    public static void test() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (B) {
                    System.out.println("aaaa");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("bbb");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
