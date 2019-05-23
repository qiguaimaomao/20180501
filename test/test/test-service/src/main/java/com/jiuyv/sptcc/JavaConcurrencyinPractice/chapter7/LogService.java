package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.jcip.annotations.GuardedBy;


public class LogService {

private final BlockingQueue<String> queue;
private final LoggerThread logger;
private final PrintWriter writer;
@GuardedBy("this")
private boolean isshutdown; 
@GuardedBy("this")
private int reservations;
public LogService(PrintWriter writer) {
	queue=new LinkedBlockingQueue<>(100);
	logger=new LoggerThread();
	this.writer=writer;
}

/**
 * 生产者可能阻塞
 * @param msg
 * @throws InterruptedException
 */
public void log(String msg) throws InterruptedException {
	synchronized (this) {
		if (isshutdown) {
			throw new IllegalStateException("logger is shutdown");
		}
		reservations++;
	}
	
		queue.put(msg);

}

public void start() {
	logger.start();

}

public void stop() {
	synchronized (this) {
		isshutdown=true;
	}
	
	logger.interrupt();

}

private class LoggerThread extends Thread{
	
	
	@Override
	public void run() {
		try {
			while (true) {
				try {
					synchronized (LogService.this) {
						if (isshutdown&&reservations==0) {
							break;
						}
						String msg=queue.take();
						synchronized (LogService.this) {
							reservations--;
						}
						writer.print(msg);
					}
					
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			}
		} finally {
			writer.close();
		}
	}
}

}
