package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.HashMap;
import java.util.Map;

public class Memoizer1<A,V> implements Computable<A,V>{
private final Map<A,V> cache=new HashMap<A,V>();
private final Computable<A,V> c;

public Memoizer1(Computable<A,V> c) {
	this.c=c;
}
	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V value=cache.get(arg);
		if (value==null) {
			value=c.compute(arg);
			cache.put(arg, value);
		}
		return value;
	}

}
