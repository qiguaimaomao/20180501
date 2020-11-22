package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.jcip.annotations.GuardedBy;

public class SemaphoreOnLock {
private final Lock lock=new ReentrantLock();
@GuardedBy("lock")
private int permits;
private final Condition permitAvailable=lock.newCondition();
public SemaphoreOnLock(int permits) {
	lock.lock();
	try {
		this.permits=permits;
	} finally {
		lock.unlock();
	}
}

public void acquire() throws InterruptedException {
	lock.lock();
	try {
		while (permits==0) {
			permitAvailable.await();
		}
		permits--;
	} finally {
		lock.unlock();
	}

}

public void release() {
	lock.lock();
	try {
		permits++;
		permitAvailable.signal();
	} finally {
		lock.unlock();
	}

}
}
