package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 不正确的中断可能导致生产者阻塞
 * @author qiguai
 *
 */
public class BrokenPrimeProducer extends Thread{
private final BlockingQueue<BigInteger> queue;
private volatile boolean canceled=false;
public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
	this.queue=queue;
}

@Override
	public void run() {
	try {
	BigInteger b=BigInteger.ONE;
	while (!canceled) {
		
			queue.put(b=b.nextProbablePrime());
		} 
	}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

public void cancel() {
	this.canceled=true;

}

public void consumePrimes() throws InterruptedException {
	BlockingQueue<BigInteger> queue1=new ArrayBlockingQueue<>(10);
	BrokenPrimeProducer bpp=new BrokenPrimeProducer(queue1);
	bpp.start();
	try {
	while (needMorePrimes()) {
		
			consume(queue.take());
		}
	}
	 finally{
			bpp.cancel();
		}

}

private void consume(BigInteger take) {
	// TODO Auto-generated method stub
	
}

private boolean needMorePrimes() {
	// TODO Auto-generated method stub
	return false;
}
}
