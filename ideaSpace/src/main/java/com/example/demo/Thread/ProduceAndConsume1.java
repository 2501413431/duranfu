package com.example.demo.Thread;

import com.sun.beans.decoder.ValueObject;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/30
 */
public class ProduceAndConsume1 {
	public static void main(String[] args) {

		int a = 6;
		System.out.println("6".equals(a));

//		String lock = new String("");
//		P p1 = new P(lock);
//		C c1 = new C(lock);
//		ThreadP threadP1 = new ThreadP(p1);
//		ThreadC threadC1 = new ThreadC(c1);
//		threadP1.start();
//		threadC1.start();
	}
}

class ThreadC extends Thread {
	private C c;

	public ThreadC(C c) {
		this.c = c;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			c.getValue();
		}
	}
}

class ThreadP extends Thread {
	private P p;

	public ThreadP(P p) {
		this.p = p;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			p.setValue();
		}
	}
}

class C {
	private String lock;

	public C(String lock) {
		this.lock = lock;
	}

	public void getValue() {
		try {
			synchronized (lock) {
				if (ObjectValue.value.equals("")) {
					lock.wait();
				}
				System.out.println("get的值是" + ObjectValue.value);
				ObjectValue.value = "";
				lock.notify();
			}
		} catch (Exception e) {

		}
	}
}


class P {
	private String lock;

	public P(String lock) {
		this.lock = lock;
	}

	public void setValue() {
		try {
			synchronized (lock) {
				if (!ObjectValue.value.equals("")) {
					lock.wait();
				}
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println("set的值是" + value);
				ObjectValue.value = value;
				lock.notify();
			}
		} catch (Exception e) {

		}
	}
}

class ObjectValue {
	public static String value = "";
}
