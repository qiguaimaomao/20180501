package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import java.util.concurrent.atomic.AtomicReference;

import net.jcip.annotations.Immutable;

public class CASNumberRange {
	@Immutable
private static class IntPair{
	private final int lower;
	private final int upper;
	public IntPair(int lower1,int upper1) {
		lower=lower1;
		upper=upper1;
	}
}
	
	private final AtomicReference<IntPair> values=new AtomicReference<CASNumberRange.IntPair>(new IntPair(0, 0));
	public int getUpper() {
		return values.get().upper;

	}
	
	public int getLower(){
		return values.get().lower;
	}
	
	public void setLower(int i) {
		while (true) {
			IntPair oldv=values.get();
			if (oldv.upper<i) {
				throw new IllegalArgumentException("setLower can't more than upper");
			}
			IntPair newv=new IntPair(i, oldv.upper);
			if (values.compareAndSet(oldv, newv)) {
				return;
			}
		}

	}
}
