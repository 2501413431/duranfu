package com.example.demo.study.jdk8.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by admin on 2018/8/7.
 */
public class ChatServer implements Runnable {

	private Selector selector;

	private SelectionKey serverKey;

	private Vector<String> usernames;

	private static final int PORT = 9999;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		new Thread(server).start();
	}
	public ChatServer(){
		usernames = new Vector<String>();
		init();
	}
	public void init() {
		try {
			selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			ServerSocket socket = serverSocketChannel.socket();
			socket.bind(new InetSocketAddress(PORT));

			serverSocketChannel.configureBlocking(false);
			serverKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("server starting.......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while (true) {
			//获取就绪channel
			int count = 0;
			try {
				count = selector.select();
				if (count > 0) {
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						//如果此key的通道是等待接受新的套接字链接
						if (key.isAcceptable()) {
							System.out.println(key.toString() + ":接收");
							//一定要把这个accept状态的服务器去掉否则会出错
							iterator.remove();
							ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
							SocketChannel socket = serverChannel.accept();
							socket.configureBlocking(false);
							socket.register(selector,SelectionKey.OP_READ);
						}
						//若此key的通道是有数据可读状态
						if(key.isValid() && key.isReadable()){
							System.out.println(key.toString() + " : 读");
							readMsg(key);
						}
						//若此key的通道是写数据状态
						if(key.isValid() && key.isWritable()){
							System.out.println(key.toString() + " : 写");
							writeMsg(key);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void readMsg(SelectionKey key) {
		SocketChannel channel = null;
		channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		try {
			int count = channel.read(buffer);
			StringBuffer buf = new StringBuffer();
			if (count > 0) {
				buffer.flip();
				buf.append(new String(buffer.array(),0 ,count));
			}
			String msg = buf.toString();
			//如果此数据是客户端连接时发送的数据
			if (msg.indexOf("open_") != -1) {
				String name = msg.substring(5);
				printInfo(name + " --> online");
				usernames.add(name);
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey skey = iter.next();
					if (skey != serverKey) {
						skey.attach(usernames);
						skey.interestOps(skey.interestOps() | SelectionKey.OP_WRITE);
					}
				}
			} else if (msg.indexOf("exit_") != -1){
				String username = msg.substring(5);
				usernames.remove(username);
				key.attach("close");

				key.interestOps(SelectionKey.OP_WRITE);
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey skey = iter.next();
					skey.attach(usernames);
					skey.interestOps(skey.interestOps() | SelectionKey.OP_WRITE);
				}
			} else {
				String uname = msg.substring(0, msg.indexOf("^"));
				msg = msg.substring(msg.indexOf("^") + 1);
				printInfo("("+uname+")说：" + msg);
				String dateTime = sdf.format(new Date());
				String smsg = uname + " " + dateTime + "\n  " + msg + "\n";
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while(iter.hasNext()){
					SelectionKey sKey = iter.next();
					sKey.attach(smsg);
					sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
				}
			}
			buffer.clear();
		} catch (Exception e) {
			e.printStackTrace();
			//当客户端关闭channel时，服务端再往通道缓冲区中写或读数据，都会报IOException，解决方法是：在服务端这里捕获掉这个异常，并且关闭掉服务端这边的Channel通道
			key.cancel();
			try {
				channel.socket().close();
				channel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}


	}

	private void writeMsg(SelectionKey key) {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		Object attachment = key.attachment();
		//获取key的值后，要把key的值置空，避免下次使用
		key.attach("");
		try {
			socketChannel.write(ByteBuffer.wrap(attachment.toString().getBytes()));
			key.interestOps(SelectionKey.OP_READ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printInfo(String str) {
		System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
	}
}
