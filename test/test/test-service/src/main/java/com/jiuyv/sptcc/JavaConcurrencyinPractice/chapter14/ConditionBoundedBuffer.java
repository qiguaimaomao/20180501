package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.jcip.annotations.GuardedBy;

public class ConditionBoundedBuffer<T> {

	protected final Lock lock=new ReentrantLock();
	private final Condition notFull=lock.newCondition();
	private final Condition notEmpty=lock.newCondition();
	@GuardedBy("lock")
	private final T[] items=(T[]) new Object[10];
	@GuardedBy("lock")
	private int tail,head,count;
	
	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			while (tail==items.length) {
				notFull.await();
			}
			items[tail]=t;
			if (++tail==items.length) {
				tail=0;
			}
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	
	public T  take() throws InterruptedException {
		lock.lock();
		try {
			while (count==0) {
				notEmpty.await();
			}
			T t=items[head];
			count--;
			items[head]=null;
			if (++head==items.length) {
				head=0;
			}
			notFull.signal();
			return t;
		} finally {
			lock.unlock();
		}

	}
}
