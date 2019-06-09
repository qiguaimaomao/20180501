package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

public class CASCounter {
private SimulatedCAS1 value;
public int get() {
	return value.get();

}

public int increment() {
	int v;
	do {
		v=value.get();
	} while (v!=value.compareAndSwap(v, v+1));

	return v+1;
}
}
