package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SimulatedCAS1 {
	@GuardedBy("this")
private int value;

	public synchronized int get() {
		return value;

	}
	
	public synchronized int compareAndSwap(int expectedvalue,int newvalue) {
		int oldvalue=value;
		if (oldvalue==expectedvalue) {
			value=newvalue;
		}
		return oldvalue;

	}
	
	public boolean compareAndSet(int expectedvalue,int newvalue) {
		return compareAndSwap(expectedvalue, newvalue)==expectedvalue;

	}
}
