package com.example.demo.Thread;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/30
 */
public class AutoDemo {
	public static void main(String[] args) throws Exception {
		final  String CHARSET = "UTF-8";
		byte[] a = "\\aaaa".getBytes();
		String s = new String(a, CHARSET);
		System.out.println("------------");
	}

}
