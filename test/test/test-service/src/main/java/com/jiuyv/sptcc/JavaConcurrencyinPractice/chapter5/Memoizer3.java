package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer3<A,V> implements Computable<A,V>{
	private final Map<A, Future<V>> cache=new ConcurrentHashMap<A,Future<V>>();
	private final Computable<A,V> c;

	public Memoizer3(Computable<A,V> c) {
		this.c=c;
	}
		@Override
		public  V compute(final A arg) throws InterruptedException {
			Future<V> value=cache.get(arg);
			if (value==null) {
				Callable<V> call=new Callable<V>() {

					@Override
					public V call() throws Exception {
						return c.compute(arg);
					}
				};
				FutureTask<V> ft=new FutureTask<>(call);
				value=ft;
				cache.put(arg, value);
				ft.run();
			}
			try {
				return value.get();
			} catch (ExecutionException e) {
				Throwable t=e.getCause();
				throw launderThrowable(t);
			}
		}

		private RuntimeException launderThrowable(Throwable t) {
			if (t instanceof RuntimeException) {
				return (RuntimeException)t;
			}else if (t instanceof Error)
		        throw (Error)t;
			else {
				return new IllegalStateException("not uncheckde",t);
			}
		}
}
