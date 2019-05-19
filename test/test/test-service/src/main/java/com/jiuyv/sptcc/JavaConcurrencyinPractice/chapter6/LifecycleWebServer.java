package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {
private final ExecutorService exec=Executors.newCachedThreadPool();

public void start() throws IOException {
	ServerSocket ss=new ServerSocket(80);
	while (!exec.isShutdown()) {
	try {
		final Socket s=ss.accept();
		exec.execute(()-> handleRequest(s));
			
		//在关闭后被提交到线程池的任务，会被拒绝执行处理器处理，可能会抛出RejectedExecutionException异常
	} catch (RejectedExecutionException e) {
		if (!exec.isShutdown()) {
			System.out.println("task submission rejected");
		}
	}
	}
}

public void stop() {
	exec.shutdown();
}

private void handleRequest(Socket s) {
	
}

}
