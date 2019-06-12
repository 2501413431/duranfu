package com.example.demo.Thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/11/5
 */
public class JoinDemo {
    public static void main(String[] args) {
        try {
            ReentrantLock lock = new ReentrantLock();
            MyThread1 myThread1 = new MyThread1();
            myThread1.start();
            myThread1.join();
            System.out.println("hahah");
        } catch (Exception e) {

        }

    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        try {
            int secondValue =  (int)(Math.random() * 10000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
