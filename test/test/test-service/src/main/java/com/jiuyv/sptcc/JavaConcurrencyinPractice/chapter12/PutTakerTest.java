package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter12;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


import com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11.BoundedBuffer;

public class PutTakerTest{
private static final ExecutorService es=Executors.newCachedThreadPool();
private final AtomicInteger putsum=new AtomicInteger();
private final AtomicInteger takesum=new AtomicInteger();
private final CyclicBarrier cb;
private final BoundedBuffer<Integer> bb;
private final int npairs,ntrails;
public PutTakerTest(int capacity,int npairs,int ntrails) {
	this.cb=new CyclicBarrier(2*npairs+1);
	this.bb=new BoundedBuffer<Integer>(capacity);
	this.npairs=npairs;
	this.ntrails=ntrails;
}

private void test() {
	try {
		for (int i = 0; i < npairs; i++) {
			es.submit(new Producer());
			es.submit(new Consumer());
		}
		cb.await();//等待所有任务启动
		cb.await();//等待所有任务完成
		System.out.println(putsum.get());
		System.out.println(takesum.get());
		System.out.println(putsum.get()==takesum.get());
	} catch (Exception e) {
		throw new RuntimeException(e);
	}

}

private int xorShift(int y) {
	y^=(y<<6);
	y^=(y>>>6);
	y^=(y<<7);
	return y;
}

class Producer implements Runnable{

	@Override
	public void run() {
		try {
			int seed=(this.hashCode()^(int)System.nanoTime());
			int sum=0;
			cb.await();
			for (int i = ntrails; i >0; --i) {
				bb.put(seed);
				sum+=seed;
				seed=xorShift(seed);
			}
			putsum.getAndAdd(sum);
			cb.await();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}

class Consumer implements Runnable{

	@Override
	public void run() {
		try {
			int sum=0;
			cb.await();
			for (int i = ntrails; i >0; --i) {
				sum+=bb.take();
			}
			takesum.getAndAdd(sum);
			cb.await();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}



public static void main(String[] args) {
	new PutTakerTest(10, 10, 10000).test();
	es.shutdown();
}
}
