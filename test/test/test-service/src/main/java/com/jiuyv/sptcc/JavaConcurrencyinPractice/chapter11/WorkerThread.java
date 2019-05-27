package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11;

import java.util.concurrent.BlockingQueue;
/**
 * 所有的并发程序都有一些串行源
 * @author qiguai
 *
 */
public class WorkerThread extends Thread{
private final BlockingQueue<Runnable> queue;
public WorkerThread(BlockingQueue<Runnable> queue) {
	this.queue=queue;
}

@Override
	public void run() {
		while (true) {
			
			try {
				Runnable task = queue.take();
				task.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}
}
