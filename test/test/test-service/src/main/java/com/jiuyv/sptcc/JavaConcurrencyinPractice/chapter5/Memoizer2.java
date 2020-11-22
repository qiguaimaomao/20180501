package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2<A,V> implements Computable<A,V>{
	private final Map<A,V> cache=new ConcurrentHashMap<A,V>();
	private final Computable<A,V> c;

	public Memoizer2(Computable<A,V> c) {
		this.c=c;
	}
		@Override
		public  V compute(A arg) throws InterruptedException {
			V value=cache.get(arg);
			if (value==null) {
				value=c.compute(arg);
				cache.put(arg, value);
			}
			return value;
		}

}
