package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockRandom extends PseudoRandom{
private final ReentrantLock lock=new ReentrantLock();
private int seed;
public ReentrantLockRandom(int seed) {
	this.seed=seed;
}

public int nextInt(int n) {
	lock.lock();
	try {
		int s=seed;
		seed=calculateNext(s);
		int result=s%n;
		return result>0?result:result+n;
	} finally {
		lock.unlock();
	}


}
}
