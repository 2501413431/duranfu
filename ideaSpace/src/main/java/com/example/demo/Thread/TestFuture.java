package com.example.demo.Thread;

import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();// 开启线程
        Future<Integer> future = executorService.submit(new CountSum());
        try {
            System.out.println("主线程调get前");
            int sum = future.get();
            System.out.println("主线程调get后");
            System.out.println("主线程获得求和结果：" + sum);
        } catch (Exception e) {
            e.printStackTrace();
            future.cancel(true);
        }
    }
}

class CountSum implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("开始求和");
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum += i;
        }
        Thread.sleep(9000);
        System.out.println("求和结束");
        return sum;
    }

}
