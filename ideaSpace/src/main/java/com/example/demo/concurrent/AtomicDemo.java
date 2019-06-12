package com.example.demo.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/11/9
 */
public class AtomicDemo {
    public static void main(String[] args) {
        test();
    }

    /**
     * 场景：蛋糕店回馈客户，对于会员卡余额小于20的客户一次性赠送20，刺激消费，每个客户只能赠送一次
     */
    public static void test() {
//        final AtomicReference<Integer> money = new AtomicReference<>();
        final AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);
        //初始卡余额小于20
//        money.set(19);
        //模拟多个线程更新数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
//                            Integer m = money.get();
                            Integer m = money.getReference();
                            if (m < 20) {
                                if(money.compareAndSet(m, m+20, timestamp, timestamp + 1)) {
                                    System.out.println("余额小于20，充值成功。余额：" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                System.out.println("余额大于20,无需充值");
                                break;
                            }
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        //用户消费进程，模拟消费行为
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    while (true) {
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m-10, timestamp, timestamp + 1)) {
                                System.out.println("成功消费10，卡余额：" + money.getReference());
                                break;
                            }
                        } else {
                            System.out.println("余额不足");
                            break;
                        }
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}


