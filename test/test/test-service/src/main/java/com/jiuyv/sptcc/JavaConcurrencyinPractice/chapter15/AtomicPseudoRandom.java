package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPseudoRandom extends PseudoRandom{
private final AtomicInteger seed;
public AtomicPseudoRandom(int seed) {
	this.seed=new AtomicInteger(seed);
}

@Override
	protected int nextInt(int n) {
		while (true) {
			int s=seed.get();
			int nexts=calculateNext(s);
			
			if (seed.compareAndSet(s, nexts)) {
				int result=s%n;
				return result>0?result:result+n;

			}
		}
	}
}
