package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected BoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			wait();
		}

		this.doPut(v);
		notifyAll();
	}
	
	public synchronized V get() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
		
        notifyAll();
        return this.doGet();
	}
	
	
}
