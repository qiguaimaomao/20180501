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
}
