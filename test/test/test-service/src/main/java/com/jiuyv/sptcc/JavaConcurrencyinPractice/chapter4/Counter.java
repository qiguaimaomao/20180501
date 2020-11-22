package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public final class Counter {
	@GuardedBy("this")
private long value=0;

	
public synchronized long getValue() {
	return value;
}

public synchronized long increment() {
	return ++this.value;
}

}
