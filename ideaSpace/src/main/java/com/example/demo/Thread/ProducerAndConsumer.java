//package com.example.demo.Thread;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2018/10/29
// */
//public class ProducerAndConsumer {
//	public static void main(String[] args) {
//		String lock = new String("");
//		P p = new P(lock);
//		C c = new C(lock);
//		ThreadP threadP = new ThreadP(p);
//		ThreadC threadC = new ThreadC(c);
//		threadP.start();
//		threadC.start();
//	}
//}
//
//class ThreadC extends Thread {
//	private C c;
//
//	public ThreadC(C c) {
//		this.c = c;
//	}
//
//	@Override
//	public void run() {
//		while (true) {
//			this.c.getValue();
//		}
//
//	}
//}
//
//class ThreadP extends Thread {
//	private P p;
//	public ThreadP(P p) {
//		this.p = p;
//	}
//
//	@Override
//	public void run() {
//		while (true) {
//			p.setValue();
//		}
//	}
//}
//
//
//class C {
//	private String lock;
//	public C(String locak) {
//		this.lock = locak;
//	}
//	public void getValue() {
//		try {
//			synchronized (lock) {
//				if (ValueObject.value.equals("")) {
//					lock.wait();
//				}
//				System.out.println("get的值是" + ValueObject.value);
//				ValueObject.value = "";
//				lock.notify();
//			}
//		} catch (Exception e) {
//
//		}
//
//
//	}
//
//}
//
//class P {
//	//生产者和消费者公用一把锁
//	private String lock;
//	public P(String lock) {
//		this.lock = lock;
//	}
//
//	public void setValue() {
//		try {
//			//设值和取值得方法加锁
//			synchronized (lock) {//如果队列里有值，则生产者一直等待
//				if (!ValueObject.value.equals("")) {
//					lock.wait();
//				}
//				String value = System.currentTimeMillis()+"_"+System.nanoTime();
//				System.out.println("set的值是" + value);
//				ValueObject.value = value;
//				lock.notify();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}
//
//class ValueObject {
//	public static String value = "";
//}
