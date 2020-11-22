package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread{
	private final BlockingQueue<BigInteger> queue;
	public PrimeProducer( BlockingQueue<BigInteger> queue) {
		this.queue=queue;
	}
@Override
public void run() {
	try {
	BigInteger b=BigInteger.ONE;
	while (!Thread.currentThread().isInterrupted()) {
		
			queue.put(b=b.nextProbablePrime());
		
	}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void cancel() {
	interrupt();

}

/**
 * 对于不能结束的任务，在本地保存中断状态，并在返回前恢复线程的中断状态，而不是马上恢复
 * @param queue
 * @return
 */
public Task getNextTask(BlockingQueue<Task> queue) {
	boolean interrupted=false;
	try {
		while (true) {
			try {
				return queue.take();
			} catch (InterruptedException e) {
				interrupted=true;
			}
		}
	} finally {
		if (interrupted) {
			Thread.currentThread().interrupt();
		}
	}
	
	// TODO Auto-generated method stub

}
}
