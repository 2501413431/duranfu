package com.example.demo.Thread;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/29
 */
public class MyThreadTest {
	public static void main(String[] args) throws Exception{
		String a = "2018";
		String b = "bbbb";
		System.out.println(a +"/" + b);
	}
}

class MyObject  {
	private String username = "1";
	private String password = "11";
	public void setValue(String u, String p) {
		this.username = u;
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("停止A线程");
			Thread.currentThread().suspend();
		}
		this.password = p;
	}
	public void printUAndP() {
		System.out.println(username + "" +password);
	}
}

class MyThread extends Thread {
	private long i = 0;

	public long getI() {
		return i;
	}

	public void setI(long i) {
		this.i = i;
	}

	@Override
	public void run() {
		while (true) {
			i++;
		}
	}
}


