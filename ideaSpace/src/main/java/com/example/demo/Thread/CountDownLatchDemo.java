package com.example.demo.Thread;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/25
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws Exception {
//		try {
//			Mythread mythread = new Mythread();
//			mythread.start();
//			for (int i = 0; i < 300; i++) {
//				System.out.println("111");
//			}
//			mythread.interrupt();
//			System.out.println("是否停止1？" + mythread.isInterrupted());
//		} catch (Exception e) {
//		}
		testDemo();
	}

	public static void testDemo() {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
//				countDownLatch.countDown();
				System.out.println(2);
//				countDownLatch.countDown();
			}
		}).start();
//		try {
//			countDownLatch.await();
//		} catch (InterruptedException e) {
//		}
		System.out.println(3);
	}
}

class Mythread extends Thread {
	@Override
	public void run() {
		super.run();
		while (true) {
			if (this.isInterrupted()) {
				System.out.println("线程停止了");
				return;
			}
			System.out.println("timer=" + System.currentTimeMillis());
		}

	}
}
class Boss implements Runnable {
	private CountDownLatch countDownLatch;
	public Boss(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			System.out.println("老板等着员工做完。。。。");
			this.countDownLatch.await();
			System.out.println("员工全部完工，老板开始检查");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

class Worker implements Runnable {

	private CountDownLatch countDownLatch;

	private String name;

	public Worker(CountDownLatch countDownLatch, String name) {
		this.countDownLatch = countDownLatch;
		this.name = name;
	}

	@Override
	public void run() {
		this.doWork();
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.name + "活干完了");
		this.countDownLatch.countDown();
	}

	private void doWork() {
		System.out.println(this.name + "正在干活");
	}

}




