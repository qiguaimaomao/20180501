package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

import com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11.BoundedBuffer;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	
	public  void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isFull()) {
					this.doPut(v);
					return;
				}	
			}
			Thread.sleep(100);
		}

	}
	
	public synchronized V get() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isEmpty()) {
					return this.doGet();
				}	
			}
			Thread.sleep(100);
		}

	}
}
