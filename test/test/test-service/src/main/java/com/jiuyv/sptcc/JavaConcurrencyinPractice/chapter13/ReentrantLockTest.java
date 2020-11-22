package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	//线程可以按照请求的顺序获得锁
	private static 	ReentrantLock rt=new ReentrantLock(true);

	public boolean trySendOnSharedLine(String message,long timeout,TimeUnit unit) throws InterruptedException {
		//可定时去获得锁
		if (!rt.tryLock(timeout, unit)) {
			return false;
		} 
		
		try {
			return doSomeThing(message);
		} finally {
			rt.unlock();
		}

	}
	
	public boolean sendOnSharedLine(String message) throws InterruptedException{
		//获得可以中断的锁
		rt.lockInterruptibly();
		try {
			return doSomeThingCancel(message);
		} finally {
			rt.unlock();
		}
		
		
	}
	
private boolean doSomeThingCancel(String message) throws InterruptedException{
		// TODO Auto-generated method stub
		return false;
	}

private boolean doSomeThing(String message) {
		// TODO Auto-generated method stub
		return false;
	}


public static void main(String[] args) {
	rt.lock();
	Condition c=rt.newCondition();
	
	try {
		
	} finally {
		rt.unlock();
	}
}
}
