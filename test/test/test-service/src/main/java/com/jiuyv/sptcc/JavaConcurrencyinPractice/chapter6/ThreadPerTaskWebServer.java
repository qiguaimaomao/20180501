package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerTaskWebServer {
/**
 * 只要请求到达的速度未超过服务器的处理能力
 * 1.执行任务的负载已经脱离了主线程，这可以让程序在完成前面的请求之前接受后面的请求，提高了响应性
 * 2.可以并行或者并发处理任务，提交了吞吐量
 * 3.任务处理代码必须是线程安全的，因为有多个任务会并发的调用它
 * @param args
 * @throws IOException
 */
public static void main(String[] args) throws IOException {
	ServerSocket server=new ServerSocket(80);
	while (true) {
		final Socket socket=server.accept();
		Runnable task=new Runnable() {
			
			@Override
			public void run() {
				handleRequest(socket);
			}
		};
		new Thread(task).start();
	}
}

private static void handleRequest(Socket socket) {
	// TODO Auto-generated method stub
	
}

}
