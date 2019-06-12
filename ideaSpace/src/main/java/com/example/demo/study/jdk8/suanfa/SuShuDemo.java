package com.example.demo.study.jdk8.suanfa;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/12/29
 * 78498
 */
public class SuShuDemo {
    public static int num;

    public static void main(String[] args) {
        sushu();
    }

    public static void sushu() {
        long start =System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 3; i <= 1000000; i+=2) {
                    boolean isSushu = true;
                    for (int j = 2; j <= Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            isSushu = false;
                            break;
                        }
                    }
                    if (isSushu) {
                        synchronized (Thread.class) {
                            num++;
                        }
                    }
                }
                countDownLatch.countDown();
            }

        };

        Runnable runnable2 = new Runnable(){
            @Override
            public void run() {
                for (int i = 2; i <= 1000000; i+=2) {
                    boolean isSushu = true;
                    for (int j = 2; j <= Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            isSushu = false;
                            break;
                        }
                    }
                    if (isSushu) {
                        synchronized (Thread.class) {
                            num++;
                        }
                    }
                }
                countDownLatch.countDown();
            }
        };
        executorService.execute(runnable1);
        executorService.execute(runnable2);

        try {
            countDownLatch.await();
            System.out.println("1000000以内求得的素数个数是："+num);
            long end =System.currentTimeMillis();
            System.out.println("并行计算所用时间："+(end-start)+"毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
