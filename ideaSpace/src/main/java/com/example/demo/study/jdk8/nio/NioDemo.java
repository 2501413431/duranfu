package com.example.demo.study.jdk8.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by admin on 2018/8/7.
 */
public class NioDemo {
	private Selector selector = null;
	public static void main(String[] args) throws Exception {
		nioTest1();
	}


	public static void selectorTest() throws Exception  {
		Selector selector = Selector.open();

	}

	public static void nioTest() throws Exception {
		File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
		FileOutputStream out = new FileOutputStream(file);
		FileChannel channel = out.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		String s = "hello nio";
		byteBuffer.put(s.getBytes());
		byteBuffer.flip();
		channel.write(byteBuffer);
		channel.close();
		out.close();
	}

	public static void nioTest1()throws Exception {
		File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
		FileInputStream in = new FileInputStream(file);
		FileChannel channel = in.getChannel();

		ByteBuffer bf = ByteBuffer.allocate((int) channel.size());
		int length = -1;
		while ((length = channel.read(bf)) > 0) {
			bf.clear();
			byte[] bytes = bf.array();
			System.out.write(bytes, 0, length);
			System.out.println();

			System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()
					+ "位置是：" + bf.position());
		}

		channel.close();
		in.close();
	}
}
