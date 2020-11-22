package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriter {
private final BlockingQueue<String> queue;
private final LoggerThread logger;
private boolean isshutdown; 
public LogWriter(Writer writer) {
	queue=new LinkedBlockingQueue<>(100);
	logger=new LoggerThread(writer);
}

/**
 * 生产者可能阻塞
 * @param msg
 * @throws InterruptedException
 */
public void log(String msg) throws InterruptedException {
	if (!isshutdown) {
		queue.put(msg);
	}
	else {
		throw new IllegalStateException("logger is shutdown");
	}

}

public void start() {
	logger.start();

}

public void stop() {
	isshutdown=true;
	logger.interrupt();

}

private class LoggerThread extends Thread{
	private final PrintWriter writer;
	public LoggerThread(Writer writer) {
		this.writer=(PrintWriter) writer;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				writer.print(queue.take());
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}finally {
			writer.close();
		}
	}
}
}
